package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class create_new_account extends AppCompatActivity {
    private CountryCodePicker ccp;
    private EditText passwordText;
    private ImageView show_pass_btn;
    private EditText nameText;
    private EditText emailText;
    private EditText phoneText;
    private Button register;
    private CountryCodePicker countryCodePicker;


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    String name, email, phone, password,phoneNumber,CountryCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        show_pass_btn = findViewById(R.id.show_pass_btn);
        passwordText = findViewById(R.id.Password);
        nameText = findViewById(R.id.FullName);
        phoneText = findViewById(R.id.Phone);
        emailText = findViewById(R.id.Email);
        register = findViewById(R.id.Button);
        countryCodePicker = findViewById(R.id.Picker);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

    }

    public void BACK(View view) {
        Intent myIntent = new Intent(getApplicationContext(), Splach.class);
        startActivityForResult(myIntent, 0);
    }

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.show_pass_btn) {

            if (passwordText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {

                //Show Password
                passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //Hide Password
                passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }

    }


    public void GoToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivityForResult(intent, 0);
        }
    }

    public void CreateUser(View view) {
        email = emailText.getText().toString().trim();
        phone = phoneText.getText().toString().trim();
        name = nameText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        CountryCode = countryCodePicker.getSelectedCountryCode();
        phoneNumber = "+"+CountryCode+phone;


        if (!email.equals("") & !phone.equals("") & !name.equals("") & !password.equals("")) {

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User user = new User(name, email, phoneNumber, "","");
                        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(create_new_account.this, "Successful Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                    }
                                });

                    } else {
                        Toast.makeText(create_new_account.this, "check email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "data is missing", Toast.LENGTH_SHORT).show();

        }
    }
}
