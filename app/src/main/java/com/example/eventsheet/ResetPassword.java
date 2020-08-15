package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

public class ResetPassword extends AppCompatActivity {
    EditText edittext1;
    EditText edittext2;
    EditText edittext3;
    EditText edittext4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().hide();
        edittext1 = findViewById(R.id.InsertNumber1);
        edittext2 = findViewById(R.id.InsertNumber2);
        edittext3 = findViewById(R.id.InsertNumber3);
        edittext4 = findViewById(R.id.InsertNumber4);
        edittext1.setFilters(new InputFilter[]{new InputFilterMinMax("0","9")});
        edittext2.setFilters(new InputFilter[]{new InputFilterMinMax("0","9")});
        edittext3.setFilters(new InputFilter[]{new InputFilterMinMax("0","9")});
        edittext4.setFilters(new InputFilter[]{new InputFilterMinMax("0","9")});
    }
    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(myIntent, 0);
    }
}