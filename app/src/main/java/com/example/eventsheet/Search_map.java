package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityGroup;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Search_map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    Marker marker1;
    Marker marker2;

    //    ConstraintLayout dialog_constraint;
//    ConstraintLayout dialog_constraint_basic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        TextView appbar_title = findViewById(R.id.appbar_title);
        appbar_title.setText("فعاليات صغرى");
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapContainer);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
//        dialog_constraint = findViewById(R.id.dialog_constraint);
//        dialog_constraint_basic = findViewById(R.id.dialog_constraint_basic);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location1 = new LatLng(21.483070, 39.184445);
        marker1 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.483070, 39.184445))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.group_3078)));

        marker2 = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.487258, 39.186456))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.group_3078)));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location1)
                .zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.setOnMarkerClickListener(this);


    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(marker1)) {
            Dialog contacts_dialog = new Dialog(this);
            contacts_dialog.setContentView(R.layout.activity_map_details);
            Window window = contacts_dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            contacts_dialog.show();
            Toast.makeText(this, "hi hi hi", Toast.LENGTH_SHORT).show();

//            dialog_constraint_basic.setVisibility(View.VISIBLE);
//            dialog_constraint.setVisibility(View.VISIBLE);
        }
        if (marker.equals(marker2)) {
            Dialog contacts_dialog = new Dialog(this);
            contacts_dialog.setContentView(R.layout.activity_map_details);
            Window window = contacts_dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            contacts_dialog.show();
            Toast.makeText(this, "bye bye bye", Toast.LENGTH_SHORT).show();

        }
        return false;
    }

//    public void hide_Dialog(View view) {
//
//        dialog_constraint.setVisibility(View.GONE);
//        dialog_constraint_basic.setVisibility(View.GONE);
//
//    }
}