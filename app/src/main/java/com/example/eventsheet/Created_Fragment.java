package com.example.eventsheet;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Created_Fragment extends Fragment {
    protected List<Event_model> mDataset_my_created;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    My_created_events_adapter my_created_events_adapter;
    String myCreatedEventStatus;
//    protected List<String> createdEvents = new ArrayList<>();


    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return inflater.inflate(R.layout.fragment_created_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Create_events();
    }

    public void Create_events() {
        mDataset_my_created = new ArrayList<>();

        databaseReference.child("createdEvents").orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser()
                .getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot dataSnapshot = snapshot.getChildren().iterator().next();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d("TAGGGGG snapshot", "onDataChange: "+snapshot);
                    Log.d("TAGGGGG data", "onDataChange: "+data);

                    myCreatedEventStatus = data.getValue(String.class);

//                    createdEvents.add(data.getKey());

                    databaseReference.child("events").orderByKey().equalTo(data.getKey())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot data2 : snapshot.getChildren()) {

                                        Log.d("data2", "onDataChange: " + data2);
                                        Log.d("snapshot2", "onDataChange: " + snapshot);

                                        Event_model event_model = new Event_model(
                                                R.drawable.nopath___copy__79_,
                                                data2.child("eventTitle").getValue(String.class),
                                                data2.child("eventLocation").getValue(String.class),
                                                data2.child("eventStartDate").getValue(String.class),
                                                data2.child("eventEndDate").getValue(String.class),
                                                myCreatedEventStatus
                                        );
                                        event_model.setEventId(data2.getKey());
                                        mDataset_my_created.add(event_model);



                                    }
                                    mRecyclerView = getView().findViewById(R.id.recyclerView_created);
                                    mLayoutManager = new LinearLayoutManager(getContext());
                                    mRecyclerView.setLayoutManager(mLayoutManager);
                                    my_created_events_adapter = new My_created_events_adapter(getActivity(),mDataset_my_created);
                                    new ItemTouchHelper(itemSimpleCallback).attachToRecyclerView(mRecyclerView);
                                    mRecyclerView.setAdapter(my_created_events_adapter);
//                                    if (mDataset_my_created.isEmpty()){
//
//                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    ItemTouchHelper.SimpleCallback itemSimpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            mDataset_my_created.remove(viewHolder.getAdapterPosition());
            my_created_events_adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_icon))
                    .addSwipeLeftActionIcon(R.drawable.swipe_delete_icon)
                    .addSwipeLeftLabel("حذف")
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


}