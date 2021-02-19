package com.example.eventsheet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Created_Fragment extends Fragment {
    protected List<Event_model> mDataset_my_created;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    My_created_events_adapter my_created_events_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Create_events();
        return inflater.inflate(R.layout.fragment_created_, container, false);
    }

    public void Create_events() {
        mDataset_my_created = new ArrayList<>();
        mDataset_my_created.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_my_created.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_my_created.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));

        mRecyclerView = mRecyclerView.findViewById(R.id.recyclerView_created);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        my_created_events_adapter = new My_created_events_adapter(mDataset_my_created);
        mRecyclerView.setAdapter(my_created_events_adapter);
    }

}