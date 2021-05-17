package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String userId, imageurl;
    private ImageView profileImage;
    TextView name, country;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        profileImage = findViewById(R.id.profile_image);
        name = findViewById(R.id.name_text);
        country = findViewById(R.id.location_text);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("حسابي");

        TextView icon1 = findViewById(R.id.icon1);
        setTextViewDrawableColor(icon1, R.color.color_icon);
        icon1.setTextColor(Color.parseColor("#CD4A58"));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#A3CB38")));
        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);

        showData();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public void GoTo_personal_data(View view) {
        Intent intent = new Intent(getApplicationContext(), Personal_data.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_change_password(View view) {
        Intent intent = new Intent(getApplicationContext(), Change_password.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_settings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_aboutus(View view) {
        Intent intent = new Intent(getApplicationContext(), About_us.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_privacy(View view) {
        Intent intent = new Intent(getApplicationContext(), Privacy_policy.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_support(View view) {
        Intent intent = new Intent(getApplicationContext(), Support.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_my_events(View view) {
        Intent intent = new Intent(getApplicationContext(), My_Events.class);
        startActivityForResult(intent, 0);
    }

    public void GoToSearch_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Search.class);
        startActivityForResult(intent, 0);
    }

    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivityForResult(intent, 0);
    }

    public void GoTo_Profile(View view) {
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        startActivityForResult(intent, 0);
    }

    public void SignOut(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivityForResult(intent, 0);
        firebaseAuth.signOut();
        Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
    }

    private void showData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot photoPath = snapshot.child("photo");
                if (!photoPath.getValue().equals("")) {
                    Log.d("photoStatus","there is a photo");
                    imageurl = photoPath.getChildren().iterator().next().getValue(String.class);
                    profileImage.setImageBitmap(BitmapFactory.decodeFile(imageurl));
                }else{
                    Log.d("photoStatus","there is no photo for this user");
                }
                Log.d("snapshotForUser",snapshot.toString());
                name.setText(snapshot.child("name").getValue(String.class));
                country.setText(snapshot.child("country").getValue(String.class));
//                User user = snapshot.getValue(User.class);
//                name.setText(user.getName());
//                country.setText(user.getCountry());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}