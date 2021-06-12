package com.example.eventsheet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fragment_1 extends Fragment {

    DatabaseReference databaseReference;

    private String clickedEventName;
    private String eventAuthor, eventEndDate, eventFees, eventRange, eventSpec, eventStartDate,
            eventSubSpec, eventTime;

    private TextView eventStartDateView, eventEndDateView, eventTimeView, eventSubSpecView,
            eventRangeView, eventSpecView, eventFeesView, eventAuthorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
//        clickedEventName = getIntent().getExtras().getString("eventClicked");
//        Event_details event_details = new Event_details();
        clickedEventName = Event_details.getclickedEventName();

        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        Log.d("clickedEventNameee", "clickedEventName: " + clickedEventName);

        databaseReference.orderByChild("eventTitle").equalTo(clickedEventName)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        DataSnapshot data = snapshot.getChildren().iterator().next();
                        Log.d("mySnapShot", "onDataChange: "+snapshot);

                        for (DataSnapshot data:snapshot.getChildren()) {
                            Log.d("codeee", "onCreateView: first code");
                            eventAuthor = data.child("eventAuthor").getValue(String.class);
                            eventEndDate = data.child("eventEndDate").getValue(String.class);
                            eventFees = data.child("eventFees").getValue(String.class);
                            eventRange = data.child("eventRange").getValue(String.class);
                            eventSpec = data.child("eventSpec").getValue(String.class);
                            eventStartDate = data.child("eventStartDate").getValue(String.class);
                            eventSubSpec = data.child("eventSubSpec").getValue(String.class);
                            eventTime = data.child("eventTime").getValue(String.class);
                        }

                        Log.d("codeee", "onCreateView: second code");

                        eventStartDateView = getView().findViewById(R.id.mn_calender_text);
                        eventEndDateView = getView().findViewById(R.id.ela_calender_text);
                        eventTimeView = getView().findViewById(R.id.mn_clock_text);
                        eventSubSpecView = getView().findViewById(R.id.status_second_text);
                        eventSpecView = getView().findViewById(R.id.status_second_text_3);
                        eventRangeView = getView().findViewById(R.id.status_second_text_2);
                        eventFeesView = getView().findViewById(R.id.status_second_text_4);
                        eventAuthorView = getView().findViewById(R.id.person_text);

                        eventStartDateView.setText(eventStartDate);
                        eventEndDateView.setText(eventEndDate);
                        eventTimeView.setText(eventTime);
                        eventSubSpecView.setText(eventSubSpec);
                        eventSpecView.setText(eventSpec);
                        eventRangeView.setText(eventRange);
                        eventFeesView.setText(eventFees);
                        eventAuthorView.setText(eventAuthor);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;
    }
}