package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class All_Events extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    protected List<Event_model> mDataset_all_event;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_events_adapter all_event_adapter;

    private MaterialSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("فعاليات صغرى");

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        Create_events_5();

    }


    public void Map(View view) {
        Intent intent = new Intent(getApplicationContext(), All_event_map.class);
        startActivityForResult(intent, 0);
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

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:
                Log.d("whatever","works");
                Toast.makeText(this, "whatever hi hi hi hi", Toast.LENGTH_SHORT).show();
                Search_filter search_filter = new Search_filter();
//                Window window = search_filter.getWindow();
//                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//                WindowManager.LayoutParams wlp = window.getAttributes();
//                wlp.gravity = Gravity.BOTTOM;
                search_filter.show(getSupportFragmentManager(),"search_filter");
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                Log.d("whatever","not works");
                Toast.makeText(this, "whatever by by by by", Toast.LENGTH_SHORT).show();
                searchBar.closeSearch();
                break;
        }
    }

}