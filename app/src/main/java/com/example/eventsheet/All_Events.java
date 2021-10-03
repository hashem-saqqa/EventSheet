package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class All_Events extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    protected List<Event_model> mDataset_all_event;
    protected List<Event_model> mDataset_searched;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_events_adapter all_event_adapter;

    private MaterialSearchBar searchBar;

    DatabaseReference databaseReference;
    String currentDateTest, startDate, endDate, currentDate;
    Date date;
    SimpleDateFormat simpleDateFormat;
    int requestCode;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        TextView appbar_title = findViewById(R.id.appbar_title);

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        date = Calendar.getInstance().getTime();
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        currentDate = simpleDateFormat.format(date);

        requestCode = getIntent().getExtras().getInt("requestCode");
        if (requestCode == 0) {
            Create_events_5();
            appbar_title.setText("فعاليات قائمة الان");
        } else if (requestCode == 1) {
            Create_events_6();
            appbar_title.setText("فعاليات كبرى");
        } else if (requestCode == 2) {
            Create_events_7();
            appbar_title.setText("فعاليات صغرى");
        } else if (requestCode == 3) {
            Create_events_8();
            appbar_title.setText("فعاليات ترفيهية");
        } else {
            Toast.makeText(this, "wrong request Code", Toast.LENGTH_SHORT).show();
        }

    }


    public void Map(View view) {
        Intent intent = new Intent(getApplicationContext(), All_event_map.class);
        startActivityForResult(intent, 0);
    }

    private void Create_events_5() {
        mDataset_all_event = new ArrayList<>();
        currentDateTest = "03/05/2020";

        databaseReference.child("events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    startDate = dataSnapshot.child("eventStartDate").getValue(String.class);
                    endDate = dataSnapshot.child("eventEndDate").getValue(String.class);

                    if (fromDateToStringToInt(currentDateTest) - fromDateToStringToInt(startDate) >= 0 &&
                            fromDateToStringToInt(currentDateTest) - fromDateToStringToInt(endDate) < 0) {

                        mDataset_all_event.add(new Event_model(dataSnapshot.getKey(), R.drawable.nopath___copy__79_,
                                dataSnapshot.child("eventTitle").getValue(String.class),
                                dataSnapshot.child("eventLocation").getValue(String.class),
                                dataSnapshot.child("eventStartDate").getValue(String.class),
                                dataSnapshot.child("eventEndDate").getValue(String.class)));
                    }
                }

                mRecyclerView = findViewById(R.id.recyclerView_all_event);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                all_event_adapter = new All_events_adapter(All_Events.this, mDataset_all_event);
                mRecyclerView.setAdapter(all_event_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void Create_events_6() {
        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية كبرى")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        mDataset_all_event = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            mDataset_all_event.add(new Event_model(dataSnapshot.getKey(),
                                    R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }

                        mRecyclerView = findViewById(R.id.recyclerView_all_event);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        all_event_adapter = new All_events_adapter(All_Events.this, mDataset_all_event);
                        mRecyclerView.setAdapter(all_event_adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void Create_events_7() {
        mDataset_all_event = new ArrayList<>();
        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية صغرى")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mDataset_all_event.add(new Event_model(dataSnapshot.getKey(),
                                    R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }
                        mRecyclerView = findViewById(R.id.recyclerView_all_event);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        all_event_adapter = new All_events_adapter(All_Events.this, mDataset_all_event);
                        mRecyclerView.setAdapter(all_event_adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void Create_events_8() {
        mDataset_all_event = new ArrayList<>();
        databaseReference.child("events").orderByChild("eventSubType").equalTo("فعالية ترفيهية")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mDataset_all_event.add(new Event_model(dataSnapshot.getKey(),
                                    R.drawable.nopath___copy__79_,
                                    dataSnapshot.child("eventTitle").getValue(String.class),
                                    dataSnapshot.child("eventLocation").getValue(String.class),
                                    dataSnapshot.child("eventStartDate").getValue(String.class),
                                    dataSnapshot.child("eventEndDate").getValue(String.class)));
                        }
                        mRecyclerView = findViewById(R.id.recyclerView_all_event);
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        all_event_adapter = new All_events_adapter(All_Events.this, mDataset_all_event);
                        mRecyclerView.setAdapter(all_event_adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        Log.d(" the Search text", "onDataChange: " + text.toString());
        mDataset_searched = new ArrayList<>();

        for (Event_model eventModel : mDataset_all_event) {

            Log.d("the event ", "onSearchConfirmed: " + eventModel);
            Log.d("the event name", "onSearchConfirmed: " + eventModel.getMain_text());
            Log.d("the if statement", "onSearchConfirmed: " + eventModel.getMain_text().equals(text.toString()));

            if (text.toString().equals(eventModel.getMain_text())) {
                mDataset_searched.add(eventModel);
            }
        }
        Log.d("search results", "onSearchConfirmed: " + mDataset_searched);

        mRecyclerView = findViewById(R.id.recyclerView_all_event);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        all_event_adapter = new All_events_adapter(All_Events.this, mDataset_searched);
        mRecyclerView.setAdapter(all_event_adapter);

//        databaseReference.orderByValue().equalTo(text.toString()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d(" the Search results", "onDataChange: "+snapshot);
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    mDataset_searched.add(new Event_model(R.drawable.nopath___copy__79_,
//                            dataSnapshot.child("eventTitle").getValue(String.class),
//                            dataSnapshot.child("eventLocation").getValue(String.class),
//                            dataSnapshot.child("eventStartDate").getValue(String.class),
//                            dataSnapshot.child("eventEndDate").getValue(String.class)));
//                }
//                mRecyclerView = findViewById(R.id.recyclerView_all_event);
//                mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                mRecyclerView.setLayoutManager(mLayoutManager);
//                all_event_adapter = new All_events_adapter(mDataset_searched);
//                mRecyclerView.setAdapter(all_event_adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                Search_filter search_filter = new Search_filter();
//                Window window = search_filter.getWindow();
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.BOTTOM;

                search_filter.show(getSupportFragmentManager(), "search_filter");

                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                Log.d("whatever", "not works");
                Toast.makeText(this, "whatever by by by by", Toast.LENGTH_SHORT).show();
                searchBar.closeSearch();
                break;
        }

    }

    public int fromDateToStringToInt(String theDate) {
        String formatted = theDate.substring(6) + theDate.substring(3, 5) + theDate.substring(0, 2);
        return Integer.parseInt(formatted);
    }

    public void BACK(View view) {
        finish();
    }
}