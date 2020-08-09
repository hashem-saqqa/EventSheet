package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.hbb20.CountryCodePicker;

public class create_new_account extends AppCompatActivity {
    private CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        getSupportActionBar().hide();
    }
    public void BACk(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Splach.class);
        startActivityForResult(myIntent, 0);
    }
}