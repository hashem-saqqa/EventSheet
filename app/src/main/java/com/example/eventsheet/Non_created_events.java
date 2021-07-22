package com.example.eventsheet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Non_created_events extends Fragment {
    Button GoToCreateEvent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_created_events, container, false);
        GoToCreateEvent = view.findViewById(R.id.non_button);
        GoToCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CreateEvent.class);
                startActivity(intent);
            }
        });
        return view;
    }
}