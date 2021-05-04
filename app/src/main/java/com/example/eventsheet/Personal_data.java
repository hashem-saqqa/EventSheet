package com.example.eventsheet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;


public class Personal_data extends AppCompatActivity {

    ImageView profileImage;
    private Uri mImageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageReference photoReference;

    private String userId, profilePhoto, photoId, photo;
    Task task;
    StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        profileImage = findViewById(R.id.profile_image);

        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("تعديل بياناتي");
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("photo");
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
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            uploadPhoto();


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
            photoReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(profilePhoto));
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
                            photoId = databaseReference.push().getKey();
                            databaseReference.child(photoId).setValue(photo);

                            System.out.println(photo);
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "please choose photo", Toast.LENGTH_SHORT).show();

        }
    }

}
