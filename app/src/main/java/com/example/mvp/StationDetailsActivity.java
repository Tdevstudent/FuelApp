package com.example.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StationDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GasStation station;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        station = (GasStation) getIntent().getSerializableExtra("station");

        TextView name        = findViewById(R.id.name);
        TextView address     = findViewById(R.id.address);
        TextView euro95      = findViewById(R.id.euro95);
        TextView diesel      = findViewById(R.id.diesel);
        TextView lastUpdated = findViewById(R.id.lastUpdated);

        name.setText(station.getName());
        address.setText(station.getAddress());
        euro95.setText(station.getEuro95());
        diesel.setText(station.getDiesel());
        lastUpdated.setText(station.getLastUpdated());
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng location = new LatLng(station.getLatitude(), station.getLongitude());

        mMap.addMarker(new MarkerOptions().position(location).title(station.getName()));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 14));
    }
}
