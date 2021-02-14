package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    protected List<Event_model> mDataset;
    protected List<Event_model> mDataset_2;
    protected List<Event_model> mDataset_3;
    protected List<Event_model> mDataset_4;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    Event_adapter event_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

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
        startActivityForResult(intent, 0);
    }

    public void Create_events_1() {
        mDataset = new ArrayList<>();
        mDataset.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa"
                , "dont bring animal", "5/ 11/2021"
                , "6/11/2021", "hasan"));
        mDataset.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa"
                , "dont bring animal", "5/ 11/2021"
                , "6/11/2021", "hasan"));
        mDataset.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa"
                , "dont bring animal", "5/ 11/2021"
                , "6/11/2021", "hasan"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        event_adapter = new Event_adapter(mDataset);
        mRecyclerView.setAdapter(event_adapter);
    }

    public void Create_events_2() {
        mDataset_2 = new ArrayList<>();
        mDataset_2.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_2.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_2.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa"
                , "5/ 11/2021", "6/11/2021"));

        mRecyclerView = findViewById(R.id.recyclerView_2);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_2);
        mRecyclerView.setAdapter(event_adapter);
    }

    public void Create_events_3() {
        mDataset_3 = new ArrayList<>();
        mDataset_3.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_3.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_3.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa"
                , "5/ 11/2021", "6/11/2021"));

        mRecyclerView = findViewById(R.id.recyclerView_3);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_3);
        mRecyclerView.setAdapter(event_adapter);
    }

    public void Create_events_4() {
        mDataset_4 = new ArrayList<>();
        mDataset_4.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_4.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_4.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa"
                , "5/ 11/2021", "6/11/2021"));

        mRecyclerView = findViewById(R.id.recyclerView_4);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Small_event_adapter event_adapter = new Small_event_adapter(mDataset_4);
        mRecyclerView.setAdapter(event_adapter);
    }
}