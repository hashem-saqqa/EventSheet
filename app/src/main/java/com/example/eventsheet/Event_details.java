package com.example.eventsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Event_details extends AppCompatActivity implements OnMapReadyCallback {

    View line;
    DatabaseReference databaseReference;
    private static String clickedEventId;
    private String eventImage, eventTitle, eventAuthor, eventContent, eventEndDate,
            eventFees, eventLocation, eventRange, eventSpec, eventStartDate, eventSubSpec,
            eventSubType, eventTime, eventType;
    private ImageView eventImageView, cancel, confirm;
    private TextView eventTitleView, eventLocationView, eventContentView, eventStartDateView,
            eventEndDateView, eventTimeView, eventSubSpecView, eventRangeView, eventSpecView, eventFeesView,
            eventAuthorView;
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;


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

        clickedEventId = getIntent().getExtras().getString("eventClicked");

        load_data();

        Log.d("getMethod", "onCreate: " + getclickedEventName());
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
        Log.d("the click id", " : " + clickedEventId);

        databaseReference.orderByKey().equalTo(clickedEventId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("data from the clic`k", "onDataChange: " + snapshot);
                        DataSnapshot data = snapshot.getChildren().iterator().next();
                        eventTitle = data.child("eventTitle").getValue(String.class);
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
                        eventLocationView = findViewById//                        eventAuthorView = findViewById(R.id.)
                                (R.id.location_text);

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
        return clickedEventId;
    }

    public void ShareEvent(View view) {

        final Dialog SharedDialog = new Dialog(getApplicationContext());
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
                            .getUid()).child((clickedEventId)).setValue("0");

                } else if (selectedId == R.id.RadioButton2) {
                    databaseReference.child("sharedEvents").child(FirebaseAuth.getInstance().getCurrentUser()
                            .getUid()).child((clickedEventId)).setValue("1");
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
}
