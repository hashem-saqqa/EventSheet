package com.example.eventsheet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Search_map extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    Marker marker1;
    Marker marker2;

    TextView icon3;

    protected List<Event_model> mDataset_map;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_event_Map_adapter all_event_map_adapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);

        getSupportActionBar().hide();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapContainer);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        Create_events();

        icon3 = findViewById(R.id.icon3);
        setTextViewDrawableColor(icon3,R.color.color_icon);
        icon3.setTextColor(Color.parseColor("#CD4A58"));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setColorFilter(Color.argb(255, 255, 255, 255));

        FloatingActionButton floatingActionButton_search_map = findViewById(R.id.fab_search_map);
        floatingActionButton_search_map.setColorFilter(Color.argb(255, 255, 255, 255));

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(getColor(color), PorterDuff.Mode.SRC_IN));
            }
        }
    }
    public void GoToHome_icon(View view) {
        Intent intent = new Intent(getApplicationContext(),Home.class);
        startActivityForResult(intent,0);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(marker1)) {
            Dialog contacts_dialog = new Dialog(this);
            contacts_dialog.setContentView(R.layout.activity_map_details);
            Window window = contacts_dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            contacts_dialog.show();

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

        }
        return false;
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
    public void Create_events() {
        mDataset_map = new ArrayList<>();
        mDataset_map.add(new Event_model(R.drawable.nopath___copy__79_,
                "First event", "ksa"));
        mDataset_map.add(new Event_model(R.drawable.nopath___copy__79_,
                "second event", "ksa"));
        mDataset_map.add(new Event_model(R.drawable.nopath___copy__79_,
                "third event", "ksa"));

        mRecyclerView = findViewById(R.id.recyclerView_map);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        all_event_map_adapter = new All_event_Map_adapter(Search_map.this,mDataset_map);
        mRecyclerView.setAdapter(all_event_map_adapter);
    }
}