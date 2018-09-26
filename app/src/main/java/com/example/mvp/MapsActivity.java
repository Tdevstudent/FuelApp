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

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<GasStation> gasStations=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018", new LatLng(53.235381, 6.539334)));
        gasStations.add(new GasStation("BP Paddepoel","Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", new LatLng(53.229204, 6.545308)));
        gasStations.add(new GasStation("Tango Groningen Laan","LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", new LatLng(53.218189, 6.538995)));
        gasStations.add(new GasStation("Gulf","Hoendiep 94", 1.719, 1.419, "September 26, 2018", new LatLng(53.212751, 6.539237)));
        gasStations.add(new GasStation("BP","Paterswoldsweg 139", 1.709, 1.409, "September 26, 2018", new LatLng(53.203064, 6.558387)));
        gasStations.add(new GasStation("Tamoil Express","Peizerweg 9727", 1.599, 1.329, "September 26, 2018", new LatLng(53.207897, 6.538240)));
        gasStations.add(new GasStation("Tango Groningen Zuiderweg","Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", new LatLng(53.200634, 6.512217)));
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

        for (GasStation station: gasStations) {
            mMap.addMarker(new MarkerOptions().position(station.getLocation()).title(station.getName()).snippet("Euro95: " + station.getEuro95() + ", Diesel: " + station.getDiesel()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(groningen, 13));

    }
}
