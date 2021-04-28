package com.example.eventsheet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class My_Events extends AppCompatActivity {
    TextView CreatedEventText;
    TextView JoinedEventText;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__events);

        getSupportActionBar().hide();

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("فعالياتي");

        CreatedEventText = findViewById(R.id.CreatedEventText);
        JoinedEventText = findViewById(R.id.JoinedEventText);

        TextView icon2 = findViewById(R.id.icon2);
        setTextViewDrawableColor(icon2, R.color.color_icon);
        icon2.setTextColor(Color.parseColor("#CD4A58"));

        Non_created_events non_created_events = new Non_created_events();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.Created_Joined_fragments, non_created_events).commit();

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
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


    public void created_events_text(View view) {
        CreatedEventText.setTextColor(Color.parseColor("#BADC58"));
        CreatedEventText.setBackgroundColor(Color.parseColor("#ffffff"));
        JoinedEventText.setTextColor(Color.parseColor("#ffffff"));
        JoinedEventText.setBackgroundColor(Color.parseColor("#BADC58"));

        Created_Fragment created_fragment = new Created_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.Created_Joined_fragments, created_fragment).commit();

    }

    public void joined_events_text(View view) {
        CreatedEventText.setTextColor(Color.parseColor("#ffffff"));
        CreatedEventText.setBackgroundColor(Color.parseColor("#BADC58"));
        JoinedEventText.setTextColor(Color.parseColor("#BADC58"));
        JoinedEventText.setBackgroundColor(Color.parseColor("#ffffff"));

        Shared_fragment shared_fragment = new Shared_fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.Created_Joined_fragments, shared_fragment).commit();
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