package com.example.mvp;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;
import android.support.v7.app.ActionBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GasStation station;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_back);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        station = (GasStation) getIntent().getParcelableExtra("station");
        Location zernike = new Location("");
        zernike.setLatitude(53.240475);
        zernike.setLongitude(6.536173);

        TextView name        = findViewById(R.id.name);
        TextView address     = findViewById(R.id.address);
        TextView euro95      = findViewById(R.id.euro95);
        TextView diesel      = findViewById(R.id.diesel);
        TextView lastUpdated = findViewById(R.id.lastUpdated);
        TextView distance    = findViewById(R.id.distance);

        name.setText(station.getName());
        address.setText(station.getAddress());
        euro95.setText(station.getEuro95());
        diesel.setText(station.getDiesel());
        lastUpdated.setText(station.getLastUpdated());
        String distanceText = String.format("%.1f", station.getDistance(zernike)) + " km";
        distance.setText(distanceText);
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

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    public void showInMaps(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/maps/search/?api=1&query=" + station.getName() + "+" + station.getAddress()));
        startActivity(intent);
    }

    public void navigate(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + station.getName() + "+" + station.getAddress()));
        startActivity(intent);
    }

    /*public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    } */
}
