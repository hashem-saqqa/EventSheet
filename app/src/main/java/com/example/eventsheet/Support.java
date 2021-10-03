package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("مركز الخدمة");
        appbar_title.setTextColor(Color.parseColor("#000000"));

        ImageView appbar_icon = findViewById(R.id.image1);
        appbar_icon.setVisibility(View.GONE);

        ImageView back_icon = findViewById(R.id.image2);
        back_icon.setColorFilter(Color.parseColor("#000000"));

        AppBarLayout appbar_layout = findViewById(R.id.appbar_layout);
        appbar_layout.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void BACK(View view) {
        finish();
    }
}