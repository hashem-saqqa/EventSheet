package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.hbb20.CountryCodePicker;

public class create_new_account extends AppCompatActivity {
    private CountryCodePicker ccp;
    private EditText password;

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

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.show_pass_btn) {

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }

    }
}
