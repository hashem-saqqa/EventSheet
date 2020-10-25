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
        edittext1 = findViewById(R.id.InsertNumber1);
        edittext2 = findViewById(R.id.InsertNumber2);
        edittext3 = findViewById(R.id.InsertNumber3);
        edittext4 = findViewById(R.id.InsertNumber4);

    }

    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(myIntent, 0);
    }

    public void ShowBottomSheet(View view) {
        ActionBottomDialogFragment bottomsheet = new ActionBottomDialogFragment();
        bottomsheet.show(getSupportFragmentManager(),"TAG");

    }

    public void GoToHome(View view) {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivityForResult(intent,0);
    }
}