package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class Change_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("تغيير كلمة المرور");
        appbar_title.setTextColor(Color.parseColor("#000000"));

        ImageView appbar_icon = findViewById(R.id.image1);
        appbar_icon.setImageResource(R.drawable.shape);

        ImageView back_icon = findViewById(R.id.image2);
        back_icon.setColorFilter(Color.parseColor("#000000"));

        AppBarLayout appbar_layout = findViewById(R.id.appbar_layout);
        appbar_layout.setBackgroundColor(Color.parseColor("#ffffff"));

    }
}