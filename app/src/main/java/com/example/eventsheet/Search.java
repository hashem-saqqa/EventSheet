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
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {
    TextView icon3;
    protected List<Event_model> mDataset_search;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    Search_adapter search_adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Create_events();
        getSupportActionBar().hide();

        icon3 = findViewById(R.id.icon3);
        setTextViewDrawableColor(icon3,R.color.color_icon);
        icon3.setTextColor(Color.parseColor("#CD4A58"));
    }

    public void Map(View view) {
        Intent intent = new Intent(getApplicationContext(), All_event_map.class);
        startActivityForResult(intent, 0);
    }
    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivityForResult(intent,0);
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
}