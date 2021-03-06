package com.example.eventsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Event_adapter extends RecyclerView.Adapter<Event_adapter.ViewHolder> {
    private List<Event_model> DataSet;
    private OnEventListener mOnEventListener;

    public Event_adapter(List<Event_model> dataSet, OnEventListener onEventListener) {
        DataSet = dataSet;
        this.mOnEventListener = onEventListener;
    }

    @NonNull
    @Override
    public Event_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.home_basic_event_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rootView, mOnEventListener);

        // Return the completed view to render on screen
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Event_adapter.ViewHolder holder, int position) {

        Event_model model = DataSet.get(position);

        holder.Main_image.setImageResource(model.getImage());
        holder.Main_text.setText(model.getMain_text());
        holder.Location_text.setText(model.getLocation_text());
        holder.detail_text.setText(model.getDetail_text());
        holder.start_date_text.setText(model.getStart_date_text());
        holder.End_date_text.setText(model.getEnd_date_text());
        holder.Auther_text.setText(model.getAuther_text());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView Main_image;
        private final TextView Main_text;
        private final TextView Location_text;
        private final TextView detail_text;
        private final TextView start_date_text;
        private final TextView End_date_text;
        private final TextView Auther_text;
        OnEventListener onEventListener;


        public ViewHolder(@NonNull View itemView, OnEventListener onEventListener) {
            super(itemView);

            Main_image = itemView.findViewById(R.id.HS_Image);

            Main_text = itemView.findViewById(R.id.text1);

            Location_text = itemView.findViewById(R.id.location_text);

            this.detail_text = itemView.findViewById(R.id.long_text);

            this.start_date_text = itemView.findViewById(R.id.mn_calender_text);

            End_date_text = itemView.findViewById(R.id.ela_calender_text);

            Auther_text = itemView.findViewById(R.id.person_text);

            this.onEventListener = onEventListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListener {
        void onEventClick(int position);
    }

}
