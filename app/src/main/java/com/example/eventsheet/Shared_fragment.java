package com.example.eventsheet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Shared_fragment extends Fragment {
    protected List<Event_model> mDataset_attend;
    protected List<Event_model> mDataset_attend_shared;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_event_Map_adapter shared_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Create_events_attend();
        Create_events_attend_shared();
    }

    public void Create_events_attend() {
        mDataset_attend = new ArrayList<>();
        mDataset_attend.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_attend.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));

        mRecyclerView = getView().findViewById(R.id.recyclerView_attend);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        shared_adapter = new All_event_Map_adapter(mDataset_attend);
        mRecyclerView.setAdapter(shared_adapter);


    }
    public void Create_events_attend_shared() {
        mDataset_attend_shared = new ArrayList<>();
        mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_, "First event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_, "second event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_, "third event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));
        mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_, "fourth event", "ksa",
                "5/ 11/2021", "6/11/2021", "قيد المراجعة"));

        mRecyclerView = getView().findViewById(R.id.recyclerView_attend_shared);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        shared_adapter = new All_event_Map_adapter(mDataset_attend_shared);
        mRecyclerView.setAdapter(shared_adapter);

    }
}