package com.example.eventsheet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Event_details extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().add(R.id.Container,fragment_1).commit();
    }

    public void add_fragment_1(View view) {
        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().add(R.id.Container,fragment_1).commit();
    }

    public void add_fragment_2(View view) {
        Fragment_2 fragment_2 = new Fragment_2();
        getSupportFragmentManager().beginTransaction().add(R.id.Container,fragment_2).commit();

    }
}
