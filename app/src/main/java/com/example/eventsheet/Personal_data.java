package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.hbb20.CountryCodePicker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Personal_data extends AppCompatActivity {

    ImageView profileImage;
    CountryCodePicker countryCodePicker;
    EditText usernametext, emailtext, phonenumbertext, countrytext;
    String username, email, phonenumber, country, phone, countryCode;
    private Uri mImageUri;

    private StorageReference storageReference, photoReference;
    private DatabaseReference databaseReference, photoPath;

    private String userId, profilePhoto, photoId, photo, imageurl;
    Task task;
    StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        profileImage = findViewById(R.id.profile_image);
        usernametext = findViewById(R.id.username_edit);
        emailtext = findViewById(R.id.email_edit);
        phonenumbertext = findViewById(R.id.Phone);
        countrytext = findViewById(R.id.country_edit);
        countryCodePicker = findViewById(R.id.Picker);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("تعديل بياناتي");

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
        photoPath = FirebaseDatabase.getInstance().getReference("users").child(userId).child("photo");

        showImage();
    }

    public void chooseImage(View view) {
        ImagePicker.Companion.with(this)
                .galleryOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK & data != null) {

            profilePhoto = ImagePicker.Companion.getFilePath(data);
            profileImage.setImageBitmap(BitmapFactory.decodeFile(profilePhoto));
//            profilePhoto = "file://"+profilePhoto;

            photoPath.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue().equals("")) {
                        Log.d("upper if", "null case");
                    } else {
                        Log.d("upper if", "not null case");
                        imageurl = snapshot.getChildren().iterator().next().getValue(String.class);
                        deletePhoto(imageurl);
                    }
                    uploadPhoto();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }

    private String getFileExtension(String string) {
//        ContentResolver cR = getContentResolver();
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cR.getType(uri));
        String extension = string.substring(string.lastIndexOf("."));
        return extension;

    }

    private void uploadPhoto() {
        if (profilePhoto != null) {
            Log.d("profilePhoto:", profilePhoto);
            Uri file = Uri.fromFile(new File(profilePhoto));
            uploadTask = photoReference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("yup", "he is in");
                    Toast.makeText(Personal_data.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    photoReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            photo = uri.toString();
                            photoId = photoPath.push().getKey();
                            photoPath.child(photoId).setValue(photo);

                            System.out.println(photo);
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "please choose photo", Toast.LENGTH_SHORT).show();

        }
    }

    private void deletePhoto(String deletedImageUrl) {

        photoReference = FirebaseStorage.getInstance().getReferenceFromUrl(deletedImageUrl);

        Log.d("photoReference on del", photoReference.getPath());

        photoReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                photoPath.removeValue();
            }
        });
    }

    private void showImage() {
        photoPath.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.getValue().equals("")) {
                    imageurl = snapshot.getChildren().iterator().next().getValue(String.class);
                    profileImage.setImageBitmap(BitmapFactory.decodeFile(imageurl));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateProfile(View view) {
        username = usernametext.getText().toString().trim();
        email = emailtext.getText().toString().trim();
        phonenumber = phonenumbertext.getText().toString().trim();
        country = countrytext.getText().toString().trim();
        countryCode = countryCodePicker.getSelectedCountryCode();
        phone = "+" + countryCode + phonenumber;

        Map<String, Object> dataUpdated = new HashMap<>();
        dataUpdated.put("name", username);
        dataUpdated.put("email", email);
        dataUpdated.put("country", country);
        dataUpdated.put("phone_number", phone);

        databaseReference.updateChildren(dataUpdated);


    }
}
