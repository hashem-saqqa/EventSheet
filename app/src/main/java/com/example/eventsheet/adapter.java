package com.example.eventsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private List<Event_model> mContacts;

    // Pass in the contact array into the constructor
    public adapter(List<Event_model> contacts) {
        mContacts = contacts;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Inflate the custom layout
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.event_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(rootView);

        // Return the completed view to render on screen
        return viewHolder;
    }


    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

        // Get the data model based on position
        Event_model contact = mContacts.get(position);

        // Set item views based on your views and data model
        holder.imageViewPicture.setImageResource(R.drawable.nopath___copy__79_);
        holder.textViewName.setText(contact.getMain_text());
        holder.textViewNumber.setText(contact.getLocation_text());
        holder.detail_text.setText(contact.getDetail_text());
        holder.start_date_text.setText(contact.getStart_date_text());
        holder.End_date_text.setText(contact.getEnd_date_text());
        holder.Auther_text.setText(contact.getAuther_text());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        ImageView imageViewPicture;
        TextView textViewName;
        TextView textViewNumber;
        TextView detail_text;
        TextView start_date_text;
        TextView End_date_text;
        TextView Auther_text;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View rootView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(rootView);

            imageViewPicture = rootView.findViewById(R.id.HS_Image);
            textViewName = rootView.findViewById(R.id.Text1);
            textViewNumber = rootView.findViewById(R.id.location_text);

            this.detail_text = itemView.findViewById(R.id.long_text);

            this.start_date_text = itemView.findViewById(R.id.mn_calender_text);

            End_date_text = itemView.findViewById(R.id.ela_calender_text);

            Auther_text = itemView.findViewById(R.id.person_text);
        }
    }
}
