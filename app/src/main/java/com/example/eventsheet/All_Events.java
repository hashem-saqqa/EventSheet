package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class All_Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__events);
        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("فعاليات صغرى");

    }

}