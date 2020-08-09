package com.example.eventsheet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Splach extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splach);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void GoToNext(View view) {
        Intent intent = new Intent(getApplicationContext(),create_new_account.class);
        startActivity(intent);
    }
}
