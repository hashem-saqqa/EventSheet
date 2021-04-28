package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Login extends AppCompatActivity {
    private EditText phone_number;
    private CountryCodePicker countryCodePicker;
    private EditText password;
    ImageView show_pass_btn;
    Button login_button;

    FirebaseAuth Auth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    String loginemail, loginphone, loginpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = findViewById(R.id.Password);
        phone_number = findViewById(R.id.Phone);
        countryCodePicker = findViewById(R.id.Picker);
        login_button = findViewById(R.id.loginButton);
        show_pass_btn = findViewById(R.id.show_pass_btn);

        loginpassword = password.getText().toString().trim();
        loginphone = phone_number.getText().toString().trim();

        Auth = FirebaseAuth.getInstance();
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

    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), create_new_account.class);
        startActivityForResult(myIntent, 0);
    }

    public void GotoResetPassword(View view) {
        Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
        startActivityForResult(intent, 0);
    }

    public void GoToHome(View view) {


        Query query = databaseReference.child("users").orderByChild("phone_number").equalTo("0592989725");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                loginemail = snapshot.child("email").getValue(String.class);
                System.out.println(snapshot);
//                Toast.makeText(Login.this, "hi hi hi", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Login.this, loginemail, Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", loginemail);
//                Auth.signInWithEmailAndPassword(loginemail, loginpassword);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Intent intent = new Intent(getApplicationContext(), Home.class);
//        startActivityForResult(intent, 0);
    }
}