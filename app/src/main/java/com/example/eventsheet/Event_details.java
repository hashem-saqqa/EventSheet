package com.example.eventsheet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Event_details extends AppCompatActivity implements OnMapReadyCallback {

    View line;
    DatabaseReference databaseReference;
    private static String clickedEventName;
    private String eventImage, eventTitle, eventAuthor, eventContent, eventEndDate,
            eventFees, eventLocation, eventRange, eventSpec, eventStartDate, eventSubSpec,
            eventSubType, eventTime, eventType;
    private ImageView eventImageView;
    private TextView eventTitleView, eventLocationView, eventContentView, eventStartDateView,
            eventEndDateView, eventTimeView, eventSubSpecView, eventRangeView, eventSpecView, eventFeesView,
            eventAuthorView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().add(R.id.Container, fragment_1).commit();

        line = findViewById(R.id.horizontal_line2);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapContainer);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        clickedEventName = getIntent().getExtras().getString("eventClicked");

        load_data();

        Log.d("getMethod", "onCreate: "+getclickedEventName());
    }

    public void add_fragment_1(View view) {
        line.setBackgroundResource(R.drawable.multiple_colors1);
        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container, fragment_1).commit();

    }

    public void add_fragment_2(View view) {
        line.setBackgroundResource(R.drawable.multiple_colors2);
        Fragment_2 fragment_2 = new Fragment_2();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container, fragment_2).commit();

    }


    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(21.483070, 39.184445)).title("jeddah"));

    }


    private void load_data() {

        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        databaseReference.orderByChild("eventTitle").equalTo(clickedEventName)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        DataSnapshot data = snapshot.getChildren().iterator().next();
                        eventTitle = clickedEventName;
                        eventAuthor = data.child("eventAuthor").getValue(String.class);
                        eventContent = data.child("eventContent").getValue(String.class);
                        eventEndDate = data.child("eventEndDate").getValue(String.class);
                        eventFees = data.child("eventFees").getValue(String.class);
                        eventImage = data.child("eventImage").getValue(String.class);
                        eventLocation = data.child("eventLocation").getValue(String.class);
                        eventRange = data.child("eventRange").getValue(String.class);
                        eventSpec = data.child("eventSpec").getValue(String.class);
                        eventStartDate = data.child("eventStartDate").getValue(String.class);
                        eventSubSpec = data.child("eventSubSpec").getValue(String.class);
                        eventSubType = data.child("eventSubType").getValue(String.class);
                        eventTime = data.child("eventTime").getValue(String.class);
                        eventType = data.child("eventType").getValue(String.class);

                        eventImageView = findViewById(R.id.main_image);
                        eventTitleView = findViewById(R.id.event_title);
                        eventContentView = findViewById(R.id.main_text);
                        eventLocationView = findViewById(R.id.location_text);

                        eventTitleView.setText(eventTitle);
                        eventContentView.setText(eventContent);
                        eventLocationView.setText(eventLocation);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

    }

    public static String getclickedEventName() {
        return clickedEventName;
    }
}
