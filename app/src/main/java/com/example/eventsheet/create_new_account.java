package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hbb20.CountryCodePicker;

public class create_new_account extends AppCompatActivity {
    private CountryCodePicker ccp;
    private EditText password;
    ImageView show_pass_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        show_pass_btn = findViewById(R.id.show_pass_btn);
        password = findViewById(R.id.Password);


    }

    public void BACK(View view) {
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
//        if (password.getInputType() == InputType.TYPE_MASK_CLASS){
//            password.setInputType(129);
//        }else {
//            password.setInputType(InputType.TYPE_MASK_CLASS);
//        }
    }


    public void GoToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(intent,0);

    }
}
