package com.example.eventsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Map_adapter extends RecyclerView.Adapter<Map_adapter.ViewHolder>{
    private List<Event_model> DataSet;

    public Map_adapter(List<Event_model> dataSet) {
        DataSet = dataSet;
    }

    @NonNull
    @Override
    public Map_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.map_item, parent, false);

        // Return a new holder instance
        Map_adapter.ViewHolder viewHolder = new Map_adapter.ViewHolder(rootView);

        // Return the completed view to render on screen
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Map_adapter.ViewHolder holder, int position) {

        Event_model model = DataSet.get(position);

        holder.Main_image.setImageResource(model.getImage());
        holder.Main_text.setText(model.getMain_text());
        holder.Location_text.setText(model.getLocation_text());

    }

    @Override
    public int getItemCount() {
        return DataSet.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView Main_image;
        private final TextView Main_text;
        private final TextView Location_text;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Main_image = itemView.findViewById(R.id.HS_Image);

            Main_text = itemView.findViewById(R.id.Text1);

            Location_text = itemView.findViewById(R.id.location_text);


        }
    }
}
