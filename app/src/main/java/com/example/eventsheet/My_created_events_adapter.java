package com.example.eventsheet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class My_created_events_adapter extends RecyclerView.Adapter<My_created_events_adapter.ViewHolder> {
    private List<Event_model> DataSet;
    private Context mContext;
    DatabaseReference databaseReference;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    ImageView confirm;
    ImageView cancel;

    public My_created_events_adapter(Context mContext, List<Event_model> dataSet) {
        DataSet = dataSet;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public My_created_events_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.my_created_events_item, parent, false);

        // Return a new holder instance
        My_created_events_adapter.ViewHolder viewHolder = new My_created_events_adapter.ViewHolder(rootView);

        // Return the completed view to render on screen
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull My_created_events_adapter.ViewHolder holder, int position) {

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final Event_model model = DataSet.get(position);

        holder.Main_image.setImageResource(model.getImage());
        holder.Main_text.setText(model.getMain_text());
        holder.Location_text.setText(model.getLocation_text());
        holder.start_date_text.setText(model.getStart_date_text());
        holder.End_date_text.setText(model.getEnd_date_text());
        if (model.getMy_created_event_status().equals("1")) {
            holder.My_event_status.setText("تم النشر");
        } else {
            holder.My_event_status.setText("قيد المراجعة");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Event_details.class);
                intent.putExtra("eventClicked", model.getEventId());
                mContext.startActivity(intent);
            }
        });

        holder.ShareEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog SharedDialog = new Dialog(mContext);
                SharedDialog.setContentView(R.layout.share_dialog);
                Window window = SharedDialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                SharedDialog.show();

                radioButton1 = SharedDialog.findViewById(R.id.RadioButton1);
                radioButton2 = SharedDialog.findViewById(R.id.RadioButton2);
                radioGroup = SharedDialog.findViewById(R.id.RadioGroup);
                confirm = SharedDialog.findViewById(R.id.done_icon);
                cancel = SharedDialog.findViewById(R.id.cancel_icon);

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        if (selectedId == R.id.RadioButton1) {
                            databaseReference.child("sharedEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).child((model.getEventId())).setValue("0");

                        } else if (selectedId == R.id.RadioButton2) {
                            databaseReference.child("sharedEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).child((model.getEventId())).setValue("1");
                        }
                        SharedDialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedDialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView Main_image;
        private final TextView Main_text;
        private final TextView Location_text;
        private final TextView start_date_text;
        private final TextView End_date_text;
        private final TextView My_event_status;
        private final TextView ShareEvent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Main_image = itemView.findViewById(R.id.HS_Image);

            Main_text = itemView.findViewById(R.id.text1);

            Location_text = itemView.findViewById(R.id.location_text);


            this.start_date_text = itemView.findViewById(R.id.mn_calender_text);

            End_date_text = itemView.findViewById(R.id.ela_calender_text);

            My_event_status = itemView.findViewById(R.id.events_status);

            ShareEvent = itemView.findViewById(R.id.share_text);
        }
    }

}
