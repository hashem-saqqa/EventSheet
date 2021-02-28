package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Personal_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("تعديل بياناتي");
    }
}