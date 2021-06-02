package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Home extends AppCompatActivity implements Event_adapter.OnEventListener {
    protected List<Event_model> mDataset_1;
    protected List<Event_model> mDataset_2;
    protected List<Event_model> mDataset_3;
    protected List<Event_model> mDataset_4;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    Event_adapter event_adapter;
    DatabaseReference databaseReference;
    Date date;
    String currentDate, startDate, endDate, currentDateTest;
    SimpleDateFormat simpleDateFormat;

    Event_adapter.OnEventListener mContext = this;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ResourceAsColor", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().hide();


        TextView icon4 = findViewById(R.id.icon4);
        setTextViewDrawableColor(icon4, R.color.color_icon);
        icon4.setTextColor(Color.parseColor("#CD4A58"));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));

        databaseReference = FirebaseDatabase.getInstance().getReference();

        date = Calendar.getInstance().getTime();
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        currentDate = simpleDateFormat.format(date);


        Create_events_1();

        Create_events_2();

        Create_events_3();

        Create_events_4();

    }

    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(myIntent, 0);
    }

    public void GoToEvent_details(View view) {
        Intent intent = new Intent(getApplicationContext(), Event_details.class);
        startActivityForResult(intent, 0);
    }

    public void Show_all_Events(View view) {
        Intent intent = new Intent(getApplicationContext(), All_Events.class);
        intent.putExtra("requestCode", 0);
        startActivity(intent);
    }

    public void GoToSearch_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivityForResult(intent, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public void Create_events_1() {
        mDataset_1 = new ArrayList<>();
        currentDateTest = "03/05/2020";

        databaseReference.child("events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    startDate = dataSnapshot.child("eventStartDate").getValue(String.class);
                    endDate = dataSnapshot.child("eventEndDate").getValue(String.class);

                    if (fromDateToStringToInt(currentDateTest) - fromDateToStringToInt(startDate) >= 0 &&
                            fromDateToStringToInt(currentDateTest) - fromDateToStringToInt(endDate) < 0) {


                        mDataset_1.add(new Event_model(R.drawable.nopath___copy__79_,
                                dataSnapshot.child("eventTitle").getValue(String.class),
                                dataSnapshot.child("eventLocation").getValue(String.class),
                                dataSnapshot.child("eventContent").getValue(String.class),
                                dataSnapshot.child("eventStartDate").getValue(String.class),
                                dataSnapshot.child("eventEndDate").getValue(String.class),
                                dataSnapshot.child("eventAuthor").getValue(String.class)));
                    }
                }
                mRecyclerView = findViewById(R.id.recyclerView);
                mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                event_adapter = new Event_adapter(mDataset_1, mContext);
                mRecyclerView.setAdapter(event_adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Create_events_2() {

        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية كبرى")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mDataset_2 = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            mDataset_2.add(new Event_model(R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }

                        mRecyclerView = findViewById(R.id.recyclerView_2);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_2);
                        mRecyclerView.setAdapter(event_adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public void Create_events_3() {
        mDataset_3 = new ArrayList<>();
        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية صغرى")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mDataset_3.add(new Event_model(R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }
                        mRecyclerView = findViewById(R.id.recyclerView_3);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_3);
                        mRecyclerView.setAdapter(event_adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    public void Create_events_4() {
        mDataset_4 = new ArrayList<>();
        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية ترفيهية")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mDataset_4.add(new Event_model(R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }
                        mRecyclerView = findViewById(R.id.recyclerView_4);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_4);
                        mRecyclerView.setAdapter(event_adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }

    public void GoTo_my_events(View view) {
        Intent intent = new Intent(getApplicationContext(), My_Events.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(intent, 0);
    }

    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(intent, 0);
    }

    public int fromDateToStringToInt(String theDate) {
        String formatted = theDate.substring(6) + theDate.substring(3, 5) + theDate.substring(0, 2);
        return Integer.parseInt(formatted);
    }

    public void Show_all_BigEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), All_Events.class);
        intent.putExtra("requestCode", 1);
        startActivity(intent);
    }

    public void Show_all_SmallEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), All_Events.class);
        intent.putExtra("requestCode", 2);
        startActivity(intent);
    }

    public void Show_all_FunEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), All_Events.class);
        intent.putExtra("requestCode", 3);
        startActivity(intent);
    }

    @Override
    public void onEventClick(int position) {
        Log.d("eventClickedName", "onEventClick: "+mDataset_1.get(position).getMain_text());

        Intent intent = new Intent(this,Event_details.class);
        intent.putExtra("eventClicked",mDataset_1.get(position).getMain_text());
        startActivity(intent);
    }
}