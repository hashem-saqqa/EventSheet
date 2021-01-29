package com.example.eventsheet;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Event_details extends AppCompatActivity implements OnMapReadyCallback {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().add(R.id.Container,fragment_1).commit();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MapContainer);
        mapFragment.getMapAsync(this);
    }

    public void add_fragment_1(View view) {
        Fragment_1 fragment_1 = new Fragment_1();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,fragment_1).commit();
    }

    public void add_fragment_2(View view) {
        Fragment_2 fragment_2 = new Fragment_2();
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,fragment_2).commit();

    }


    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(21.483070, 39.184445)).title("jeddah"));

    }
}
