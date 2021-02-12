package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {
    protected List<Event_model> mDataset;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Create_events();
//        generateDummyContacts();
        mRecyclerView = findViewById(R.id.recyclerView_2);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Event_adapter event_adapter = new Event_adapter(mDataset);

        mRecyclerView.setAdapter(event_adapter);
    }

    public void Create_events() {
        mDataset = new ArrayList<>();
//        mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"First event","ksa"
//                ,"dont bring animal","5/ 11/2021"
//                ,"6/11/2021","hasan"));
//        mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"second event","ksa"
//                ,"dont bring animal","5/ 11/2021"
//                ,"6/11/2021","hasan"));
//        mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"third event","ksa"
//                ,"dont bring animal","5/ 11/2021"
//                ,"6/11/2021","hasan"));
        for (int i = 0; i < 3; i++) {
        mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"third event","ksa"
                ,"dont bring animal","5/ 11/2021"
                ,"6/11/2021","hasan"));
//            mDataset.add(new Event_model(R.drawable.nopath___copy__79_, "Contact #" + i, i + "" + i + "" + i));

                }

        }
//public void generateDummyContacts() {
//     mDataset = new ArrayList<>();
//    for (int i = 0; i < 3; i++) {
//        mDataset.add(new Contact(R.drawable.nopath___copy__79_, "Contact #" + i, i + "" + i + "" + i));
//    }
//    }

    }