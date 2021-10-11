package com.example.eventsheet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

public class Small_event_adapter extends RecyclerView.Adapter<Small_event_adapter.ViewHolder> {
    private List<Event_model> DataSet;
    DatabaseReference databaseReference;
    Context mContext;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    ImageView confirm;
    ImageView cancel;

    public Small_event_adapter(Context mContext, List<Event_model> dataSet) {
        DataSet = dataSet;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public Small_event_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.home_small_event_item, parent, false);

        // Return a new holder instance
        Small_event_adapter.ViewHolder viewHolder = new Small_event_adapter.ViewHolder(rootView);

        // Return the completed view to render on screen
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Small_event_adapter.ViewHolder holder, final int position) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Event_model model = DataSet.get(position);

        holder.Main_image.setImageResource(R.drawable.nopath___copy__79_);
        holder.Main_text.setText(model.getMain_text());
        holder.Location_text.setText(model.getLocation_text());
        holder.start_date_text.setText(model.getStart_date_text());
        holder.End_date_text.setText(model.getEnd_date_text());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Event_details.class);
                intent.putExtra("eventClicked", DataSet.get(position).getEventId());
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

                        Log.d("selectedId", "onClick: " + selectedId);
                        Log.d("selectedId", "onClick: " + R.id.RadioButton1);

                        if (selectedId == R.id.RadioButton1) {
                            databaseReference.child("sharedEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).child((DataSet.get(position).getEventId())).setValue("0");
                            Log.d("selectedId", "onClick: inside the R.id.RadioButton1");

                        } else if (selectedId == R.id.RadioButton2) {
                            databaseReference.child("sharedEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                                    .getUid()).child((DataSet.get(position).getEventId())).setValue("1");
                            Log.d("selectedId", "onClick: inside the R.id.RadioButton2");

                        } else {
                            Log.d("selectedId", "onClick: inside the False");

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

                Log.d("Event Id", "onClick: " + DataSet.get(position).getEventId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView Main_image;
        private final TextView Main_text;
        private final TextView Location_text;
        private final TextView start_date_text;
        private final TextView End_date_text;
        private final TextView ShareEvent;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ShareEvent = itemView.findViewById(R.id.share_text);

            Main_image = itemView.findViewById(R.id.HS_Image);

            Main_text = itemView.findViewById(R.id.text1);

            Location_text = itemView.findViewById(R.id.location_text);

            this.start_date_text = itemView.findViewById(R.id.mn_calender_text);

            End_date_text = itemView.findViewById(R.id.ela_calender_text);


        }

    }


}
