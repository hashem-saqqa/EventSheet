package com.example.eventsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class All_event_map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, MaterialSearchBar.OnSearchActionListener {
    Marker marker1;
    Marker marker2;
    protected List<Event_model> mDataset_map;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    All_event_Map_adapter all_event_map_adapter;
    private MaterialSearchBar searchBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event_map);
        TextView appbar_title = findViewById(R.id.appbar_title);

        appbar_title.setText("فعاليات صغرى");

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapContainer);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        Create_events();
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
        all_event_map_adapter = new All_event_Map_adapter(All_event_map.this, mDataset_map);
        mRecyclerView.setAdapter(all_event_map_adapter);
    }

    public void BACK(View view) {
        finish();
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode) {
            case MaterialSearchBar.BUTTON_NAVIGATION:

                Search_filter search_filter = new Search_filter();
                search_filter.show(getSupportFragmentManager(), "All_Events_map");
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                break;
            case MaterialSearchBar.BUTTON_BACK:
                searchBar.closeSearch();
                break;
        }
    }

//    public void hide_Dialog(View view) {
//
//        dialog_constraint.setVisibility(View.GONE);
//        dialog_constraint_basic.setVisibility(View.GONE);
//
//    }

}