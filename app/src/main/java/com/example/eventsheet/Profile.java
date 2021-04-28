package com.example.eventsheet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("حسابي");

        TextView icon1 = findViewById(R.id.icon1);
        setTextViewDrawableColor(icon1, R.color.color_icon);
        icon1.setTextColor(Color.parseColor("#CD4A58"));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#A3CB38")));
        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public void GoTo_personal_data(View view) {
        Intent intent = new Intent(getApplicationContext(), Personal_data.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_change_password(View view) {
        Intent intent = new Intent(getApplicationContext(), Change_password.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_settings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_aboutus(View view) {
        Intent intent = new Intent(getApplicationContext(), About_us.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_privacy(View view) {
        Intent intent = new Intent(getApplicationContext(), Privacy_policy.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_support(View view) {
        Intent intent = new Intent(getApplicationContext(), Support.class);
        startActivityForResult(intent, 0);
    }
    public void GoTo_my_events(View view) {
        Intent intent = new Intent(getApplicationContext(), My_Events.class);
        startActivityForResult(intent, 0);
    }
    public void GoToSearch_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivityForResult(intent, 0);
    }
    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivityForResult(intent,0);
    }
    public void GoTo_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(),Profile.class);
        startActivityForResult(intent,0);
    }
}