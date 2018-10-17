package com.example.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapTab extends Fragment implements OnMapReadyCallback {

    private ArrayList<GasStation> gasStations = new ArrayList<>();
    private GoogleMap mMap;
    private static final String TAB_FRAGMENT_TAG = "MAP";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_tab, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        gasStations = (ArrayList) getArguments().getParcelableArrayList("gasStations").clone();
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng groningen = new LatLng(53.213846,6.569568);

        for (GasStation station: gasStations) {
            mMap.addMarker(new MarkerOptions().position(station.getLocation()).title(station.getName()).snippet("Euro95: " + station.getEuro95() + ", Diesel: " + station.getDiesel()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(groningen,12));
    }
}
