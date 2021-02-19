package com.example.eventsheet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

        Created_Fragment created_fragment = new Created_Fragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.Created_Joined_fragments, created_fragment).commit();

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
    }
}