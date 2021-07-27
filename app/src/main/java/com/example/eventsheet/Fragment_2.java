package com.example.eventsheet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment_2 extends Fragment {
    DatabaseReference databaseReference;
    String eventStartDate;
    TextView CalenderText1,CalenderText2,CalenderText3,CalenderText4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String clickedEventId = Event_details.getclickedEventName();

        databaseReference = FirebaseDatabase.getInstance().getReference("events");
        databaseReference.orderByKey().equalTo(clickedEventId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot data : snapshot.getChildren()) {
                            eventStartDate = data.child("eventStartDate").getValue(String.class);
                        }

                        CalenderText1 = getView().findViewById(R.id.calender_text);
                        CalenderText2 = getView().findViewById(R.id.calender_text_2);
                        CalenderText3 = getView().findViewById(R.id.calender_text_3);
                        CalenderText4 = getView().findViewById(R.id.calender_text_4);

                        CalenderText1.setText(eventStartDate);
                        CalenderText2.setText(eventStartDate);
                        CalenderText3.setText(eventStartDate);
                        CalenderText4.setText(eventStartDate);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        return inflater.inflate(R.layout.fragment_2, container, false);
    }
}