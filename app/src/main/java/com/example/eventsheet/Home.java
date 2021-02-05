package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.appbar.AppBarLayout;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
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
}