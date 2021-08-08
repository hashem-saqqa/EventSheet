package com.example.eventsheet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Shared_fragment extends Fragment {
    protected List<Event_model> mDataset_attend;
    protected List<Event_model> mDataset_attend_shared;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_event_Map_adapter shared_adapter;

    int i = 0;
    int j = 0;

    TextView showAllAttend, showAllAttendShared;

    String mySharedEventStatus;

    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shared_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        Create_events_attend();
        Create_events_attend_shared();
        showAllAttend = view.findViewById(R.id.view_all_attend);
        showAllAttendShared = view.findViewById(R.id.view_all_attend_shared);
        textOnClick();
    }


    private void textOnClick() {
        showAllAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset_attend = new ArrayList<>();
                mDataset_attend_shared = new ArrayList<>();

                databaseReference.child("sharedEvents").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                        .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        DataSnapshot dataSnapshot = snapshot.getChildren().iterator().next();

                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            mySharedEventStatus = data.getValue(String.class);

                            if (mySharedEventStatus.equals("0")) {

                                databaseReference.child("events").orderByKey().equalTo(data.getKey())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                for (DataSnapshot data2 : snapshot.getChildren()) {

                                                    mDataset_attend.add(new Event_model(R.drawable.nopath___copy__79_,
                                                            data2.child("eventTitle").getValue(String.class),
                                                            data2.child("eventLocation").getValue(String.class)));
                                                }

                                                mRecyclerView = getView().findViewById(R.id.recyclerView_attend);
                                                mLayoutManager = new LinearLayoutManager(getContext());
                                                mRecyclerView.setLayoutManager(mLayoutManager);
                                                shared_adapter = new All_event_Map_adapter(mDataset_attend);
                                                mRecyclerView.setAdapter(shared_adapter);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        showAllAttendShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("sharedEvents").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                        .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        DataSnapshot dataSnapshot = snapshot.getChildren().iterator().next();

                        for (DataSnapshot data : dataSnapshot.getChildren()) {

                            mySharedEventStatus = data.getValue(String.class);

                            if (mySharedEventStatus.equals("1")) {

                                databaseReference.child("events").orderByKey().equalTo(data.getKey())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                for (DataSnapshot data2 : snapshot.getChildren()) {

                                                    mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_,
                                                            data2.child("eventTitle").getValue(String.class),
                                                            data2.child("eventLocation").getValue(String.class)));
                                                }

                                                mRecyclerView = getView().findViewById(R.id.recyclerView_attend_shared);
                                                mLayoutManager = new LinearLayoutManager(getContext());
                                                mRecyclerView.setLayoutManager(mLayoutManager);
                                                shared_adapter = new All_event_Map_adapter(mDataset_attend_shared);
                                                mRecyclerView.setAdapter(shared_adapter);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void Create_events_attend() {
        mDataset_attend = new ArrayList<>();

        databaseReference.child("sharedEvents").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot dataSnapshot = snapshot.getChildren().iterator().next();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    mySharedEventStatus = data.getValue(String.class);

                    if (mySharedEventStatus.equals("0")) {

                        databaseReference.child("events").orderByKey().equalTo(data.getKey())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for (DataSnapshot data2 : snapshot.getChildren()) {

                                            mDataset_attend.add(new Event_model(R.drawable.nopath___copy__79_,
                                                    data2.child("eventTitle").getValue(String.class),
                                                    data2.child("eventLocation").getValue(String.class)));
                                            Log.d("testt", "onDataChange: " + mDataset_attend.size());
                                        }

                                        Log.d("finalTestt", "onDataChange: " + mDataset_attend.size());
                                        if (i < 3) {
                                            i++;
                                        }
                                        mRecyclerView = getView().findViewById(R.id.recyclerView_attend);
                                        mLayoutManager = new LinearLayoutManager(getContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        mDataset_attend = mDataset_attend.subList(0, i);
                                        shared_adapter = new All_event_Map_adapter(mDataset_attend);
                                        mRecyclerView.setAdapter(shared_adapter);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void Create_events_attend_shared() {
        mDataset_attend_shared = new ArrayList<>();

        databaseReference.child("sharedEvents").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot dataSnapshot = snapshot.getChildren().iterator().next();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    mySharedEventStatus = data.getValue(String.class);

                    if (mySharedEventStatus.equals("1")) {

                        databaseReference.child("events").orderByKey().equalTo(data.getKey())
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for (DataSnapshot data2 : snapshot.getChildren()) {

                                            mDataset_attend_shared.add(new Event_model(R.drawable.nopath___copy__79_,
                                                    data2.child("eventTitle").getValue(String.class),
                                                    data2.child("eventLocation").getValue(String.class)));
                                        }
                                        if (j < 3) {
                                            j++;
                                        }
                                        mRecyclerView = getView().findViewById(R.id.recyclerView_attend_shared);
                                        mLayoutManager = new LinearLayoutManager(getContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        mDataset_attend_shared = mDataset_attend_shared.subList(0, j);
                                        shared_adapter = new All_event_Map_adapter(mDataset_attend_shared);
                                        mRecyclerView.setAdapter(shared_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}