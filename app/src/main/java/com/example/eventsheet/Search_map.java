package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

public class Search_map extends AppCompatActivity implements OnMapReadyCallback {

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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng marker1 = new LatLng(21.483070,39.184445);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.483070, 39.184445))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.group_3078)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(21.487258, 39.186456))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.group_3078)));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker1)
                .zoom(16).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}