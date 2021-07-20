package com.example.eventsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class My_created_events_adapter  extends RecyclerView.Adapter<My_created_events_adapter.ViewHolder> {
    private List<Event_model> DataSet;

    public My_created_events_adapter(List<Event_model> dataSet) {
        DataSet = dataSet;
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

        Event_model model = DataSet.get(position);

        holder.Main_image.setImageResource(model.getImage());
        holder.Main_text.setText(model.getMain_text());
        holder.Location_text.setText(model.getLocation_text());
        holder.start_date_text.setText(model.getStart_date_text());
        holder.End_date_text.setText(model.getEnd_date_text());
        if (model.getMy_created_event_status().equals("1")) {
            holder.My_event_status.setText("تم النشر");
        }else {
            holder.My_event_status.setText("قيد المراجعة");
        }

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




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Main_image = itemView.findViewById(R.id.HS_Image);

            Main_text = itemView.findViewById(R.id.text1);

            Location_text = itemView.findViewById(R.id.location_text);


            this.start_date_text = itemView.findViewById(R.id.mn_calender_text);

            End_date_text = itemView.findViewById(R.id.ela_calender_text);


            My_event_status = itemView.findViewById(R.id.events_status);
        }
    }

}
