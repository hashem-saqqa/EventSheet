package com.example.eventsheet;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {
    TextView icon3;
    protected List<Event_model> mDataset_search;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    Search_adapter search_adapter;
    private MaterialSearchBar searchBar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Create_events();
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
        mDataset_search.add(new Event_model(R.drawable.nopath___copy__79_, "First event",
                "ksa", "5/ 11/2021"));
        mDataset_search.add(new Event_model(R.drawable.nopath___copy__79_, "second event",
                "ksa", "5/ 11/2021"));
        mDataset_search.add(new Event_model(R.drawable.nopath___copy__79_, "third event",
                "ksa", "5/ 11/2021"));
        mDataset_search.add(new Event_model(R.drawable.nopath___copy__79_, "fourth event",
                "ksa", "5/ 11/2021"));
        mDataset_search.add(new Event_model(R.drawable.nopath___copy__79_, "fifth event",
                "ksa", "5/ 11/2021"));

        mRecyclerView = findViewById(R.id.recyclerView_search);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        search_adapter = new Search_adapter(mDataset_search);
        mRecyclerView.setAdapter(search_adapter);
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

    public void GoToSearch_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivityForResult(intent, 0);
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
                Log.d("whatever", "works");
                Toast.makeText(this, "whatever hi hi hi hi", Toast.LENGTH_SHORT).show();
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
}