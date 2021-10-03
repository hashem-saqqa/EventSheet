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

    String loginEmail, loginPhone, loginPassword, phoneNumber, CountryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.Password);
        phone_number = findViewById(R.id.Phone);
        countryCodePicker = findViewById(R.id.Picker);
        login_button = findViewById(R.id.loginButton);
        show_pass_btn = findViewById(R.id.show_pass_btn);


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

    public void GotoResetPassword(View view) {
        Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
        startActivity(intent);
    }

    public void GoToHome(View view) {

        loginPassword = password.getText().toString().trim();
        loginPhone = phone_number.getText().toString().trim();
        CountryCode = countryCodePicker.getSelectedCountryCode();
        phoneNumber = "+" + CountryCode + loginPhone;

        if (!loginPhone.equals("") & !loginPassword.equals("")) {

            Query query = databaseReference.child("users").orderByChild("phone").equalTo(phoneNumber);

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    System.out.println(snapshot);
                    if (snapshot.getValue() == null) {
                        Toast.makeText(Login.this, "enter valid number please", Toast.LENGTH_SHORT).show();
                    } else {
                        DataSnapshot data = snapshot.getChildren().iterator().next();
                        loginEmail = data.child("email").getValue(String.class);

                        Auth.signInWithEmailAndPassword(loginEmail, loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(Login.this, "login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            });
        } else {
            Toast.makeText(this, "Enter phone number and password ", Toast.LENGTH_SHORT).show();
        }
    }

    public void GoToCreateAccount(View view) {
        Intent intent = new Intent(Login.this, create_new_account.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, 0);
        }
    }
}