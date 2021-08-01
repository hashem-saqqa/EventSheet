package com.example.eventsheet;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class EventSheet extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
