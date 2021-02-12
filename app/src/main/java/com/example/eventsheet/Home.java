package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    protected List<Event_model> mDataset;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        Create_events();
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Event_adapter event_adapter = new Event_adapter(mDataset);
        mRecyclerView.setAdapter(event_adapter);
    }

    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(myIntent, 0);
    }

    public void GoToEvent_details(View view) {
        Intent intent = new Intent(getApplicationContext(), Event_details.class);
        startActivityForResult(intent, 0);
    }

    public void Show_all_Events(View view) {
        Intent intent = new Intent(getApplicationContext(), All_Events.class);
        startActivityForResult(intent, 0);
    }
public void Create_events(){
mDataset = new ArrayList<>();
mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"First event","ksa"
        ,"dont bring animal","5/ 11/2021"
        ,"6/11/2021","hasan"));
    mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"second event","ksa"
            ,"dont bring animal","5/ 11/2021"
            ,"6/11/2021","hasan"));
    mDataset.add(new Event_model(R.drawable.nopath___copy__79_,"third event","ksa"
            ,"dont bring animal","5/ 11/2021"
            ,"6/11/2021","hasan"));
}

}