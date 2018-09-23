package com.example.mvp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng groningen = new LatLng(53.213846,6.569568);

        LatLng station1 = new LatLng(53.212612,6.592821);
        LatLng station2 = new LatLng(53.221658,6.582410);
        LatLng station3 = new LatLng(53.221401,6.552261);

        MarkerOptions marker1 = new MarkerOptions().position(station1).title("Virtual Gas Station 1").snippet("Euro95: €1.6, Diesel €1.4");
        MarkerOptions marker2 = new MarkerOptions().position(station2).title("Virtual Gas Station 2").snippet("Euro95: €1.5, Diesel €1.3");
        MarkerOptions marker3 = new MarkerOptions().position(station3).title("Virtual Gas Station 3").snippet("Euro95: €1.7, Diesel €1.2");

        mMap.addMarker(marker1);
        mMap.addMarker(marker2);
        mMap.addMarker(marker3);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(groningen, 13));

    }
}
