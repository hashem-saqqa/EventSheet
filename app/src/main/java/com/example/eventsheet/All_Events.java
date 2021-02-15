package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class All_Events extends AppCompatActivity {
    protected List<Event_model> mDataset_all_event;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_events_adapter all_event_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__events);
        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("فعاليات صغرى");

        Create_events_5();

    }

    public void Map(View view) {
        Intent intent = new Intent(getApplicationContext(),Search_map.class);
        startActivityForResult(intent,0);
    }
    public void Create_events_5() {
        mDataset_all_event = new ArrayList<>();
        mDataset_all_event.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_all_event.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_all_event.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_all_event.add(new Event_model(R.drawable.nopath___copy__79_, "fourth event", "ksa"
                , "5/ 11/2021", "6/11/2021"));
        mDataset_all_event.add(new Event_model(R.drawable.nopath___copy__79_, "fifth event", "ksa"
                , "5/ 11/2021", "6/11/2021"));

        mRecyclerView = findViewById(R.id.recyclerView_all_event);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        all_event_adapter = new All_events_adapter(mDataset_all_event);
        mRecyclerView.setAdapter(all_event_adapter);
    }
}