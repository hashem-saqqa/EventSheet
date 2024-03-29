package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    TextView icon3;
    protected List<Event_model> mDataset_search;
    protected List<Event_model> mDataset_searched;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    Search_adapter search_adapter;
    private MaterialSearchBar searchBar;

    DatabaseReference databaseReference;

    TabLayout tabLayout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();

        icon3 = findViewById(R.id.icon3);
        setTextViewDrawableColor(icon3, R.color.color_icon);
        icon3.setTextColor(Color.parseColor("#CD4A58"));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));

        FloatingActionButton floatingActionButton_search = findViewById(R.id.fab_search);
        floatingActionButton_search.setColorFilter(Color.argb(255, 255, 255, 255));

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        Create_events();

        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("Selected tab", "onTabSelected: " + tabLayout.getSelectedTabPosition());
                Log.d("The Selected tab", "onTabSelected: " + tab);
                switch (tabLayout.getSelectedTabPosition()) {
                    case 0:
                        mDataset_search = new ArrayList<>();
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot data : snapshot.getChildren()) {

                                    Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                            data.child("eventTitle").getValue(String.class),
                                            data.child("eventLocation").getValue(String.class)
                                            , data.child("eventStartDate").getValue(String.class));
                                    event_model.setEventId(data.getKey());
                                    mDataset_search.add(event_model);
                                }
                                mRecyclerView = findViewById(R.id.recyclerView_search);
                                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                mRecyclerView.setLayoutManager(mLayoutManager);
                                search_adapter = new Search_adapter(Search.this, mDataset_search);
                                mRecyclerView.setAdapter(search_adapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case 1:
                        mDataset_search = new ArrayList<>();
                        databaseReference.orderByChild("eventType").equalTo("مؤتمر")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                                    data.child("eventTitle").getValue(String.class),
                                                    data.child("eventLocation").getValue(String.class)
                                                    , data.child("eventStartDate").getValue(String.class));
                                            event_model.setEventId(data.getKey());
                                            mDataset_search.add(event_model);
                                        }
                                        mRecyclerView = findViewById(R.id.recyclerView_search);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        search_adapter = new Search_adapter(Search.this, mDataset_search);
                                        mRecyclerView.setAdapter(search_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        break;
                    case 2:
                        mDataset_search = new ArrayList<>();
                        databaseReference.orderByChild("eventType").equalTo("فعالية")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                                    data.child("eventTitle").getValue(String.class),
                                                    data.child("eventLocation").getValue(String.class)
                                                    , data.child("eventStartDate").getValue(String.class));
                                            event_model.setEventId(data.getKey());
                                            mDataset_search.add(event_model);
                                        }
                                        mRecyclerView = findViewById(R.id.recyclerView_search);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        search_adapter = new Search_adapter(Search.this, mDataset_search);
                                        mRecyclerView.setAdapter(search_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        break;
                    case 3:
                        mDataset_search = new ArrayList<>();
                        databaseReference.orderByChild("eventType").equalTo("ندوة")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                                    data.child("eventTitle").getValue(String.class),
                                                    data.child("eventLocation").getValue(String.class)
                                                    , data.child("eventStartDate").getValue(String.class));
                                            event_model.setEventId(data.getKey());
                                            mDataset_search.add(event_model);
                                        }
                                        mRecyclerView = findViewById(R.id.recyclerView_search);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        search_adapter = new Search_adapter(Search.this, mDataset_search);
                                        mRecyclerView.setAdapter(search_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        break;
                    case 4:
                        mDataset_search = new ArrayList<>();
                        databaseReference.orderByChild("eventType").equalTo("دورة")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                                    data.child("eventTitle").getValue(String.class),
                                                    data.child("eventLocation").getValue(String.class)
                                                    , data.child("eventStartDate").getValue(String.class));
                                            event_model.setEventId(data.getKey());
                                            mDataset_search.add(event_model);
                                        }
                                        mRecyclerView = findViewById(R.id.recyclerView_search);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        search_adapter = new Search_adapter(Search.this, mDataset_search);
                                        mRecyclerView.setAdapter(search_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        break;
                    case 5:
                        mDataset_search = new ArrayList<>();
                        databaseReference.orderByChild("eventType").equalTo("مبادرة")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot data : snapshot.getChildren()) {
                                            Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                                                    data.child("eventTitle").getValue(String.class),
                                                    data.child("eventLocation").getValue(String.class)
                                                    , data.child("eventStartDate").getValue(String.class));
                                            event_model.setEventId(data.getKey());
                                            mDataset_search.add(event_model);
                                        }
                                        mRecyclerView = findViewById(R.id.recyclerView_search);
                                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                        mRecyclerView.setLayoutManager(mLayoutManager);
                                        search_adapter = new Search_adapter(Search.this, mDataset_search);
                                        mRecyclerView.setAdapter(search_adapter);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        break;

                    default:

                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        TabLayout tabLayout =   findViewById(R.id.tab_layout);
//        TabLayout.Tab tab = tabLayout.getTabAt(6);
//        tab.select();
    }

    public void Map(View view) {
        Intent intent = new Intent(getApplicationContext(), All_event_map.class);
        startActivityForResult(intent, 0);
    }

    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
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

    public void Create_events() {
        mDataset_search = new ArrayList<>();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Event_model event_model = new Event_model(R.drawable.nopath___copy__79_,
                            data.child("eventTitle").getValue(String.class),
                            data.child("eventLocation").getValue(String.class)
                            , data.child("eventStartDate").getValue(String.class));
                    event_model.setEventId(data.getKey());
                    mDataset_search.add(event_model);
                }
                mRecyclerView = findViewById(R.id.recyclerView_search);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                search_adapter = new Search_adapter(Search.this, mDataset_search);
                mRecyclerView.setAdapter(search_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void GoTo_Search_map(View view) {
        Intent intent = new Intent(getApplicationContext(), Search_map.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_my_events(View view) {
        Intent intent = new Intent(getApplicationContext(), My_Events.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_CreateEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateEvent.class);
        startActivity(intent);
    }

    public void GoToSearch_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        Log.d(" the Search text", "onDataChange: " + text.toString());
        mDataset_searched = new ArrayList<>();

        for (Event_model eventModel : mDataset_search) {

            if (text.toString().equals(eventModel.getMain_text())) {
                mDataset_searched.add(eventModel);
            }
        }
        Log.d("search results", "onSearchConfirmed: " + mDataset_searched);

        mRecyclerView = findViewById(R.id.recyclerView_search);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        search_adapter = new Search_adapter(Search.this, mDataset_searched);
        mRecyclerView.setAdapter(search_adapter);

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:

                Search_filter search_filter = new Search_filter();
                search_filter.show(getSupportFragmentManager(), "Search");

                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.closeSearch();
                break;
        }

    }
}