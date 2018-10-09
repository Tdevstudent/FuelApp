package com.example.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

public class StationDetailsActivity extends AppCompatActivity {

    private GasStation station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

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
}
