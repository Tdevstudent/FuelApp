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

        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018", 53.235381, 6.539334));
        gasStations.add(new GasStation("BP Paddepoel","Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", 53.229204, 6.545308));
        gasStations.add(new GasStation("Gulf","Hoendiep 94", 1.719, 1.419, "September 26, 2018", 53.212751, 6.539237));
        gasStations.add(new GasStation("BP","Paterswoldsweg 139", 1.709, 1.409, "September 26, 2018", 53.203064, 6.558387));
        gasStations.add(new GasStation("Tamoil Express","Peizerweg 9727", 1.599, 1.329, "September 26, 2018", 53.207897, 6.538240));
        gasStations.add(new GasStation("Tango Groningen Zuiderweg","Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", 53.200634, 6.512217));
        gasStations.add(new GasStation("Tango Groningen Laan","LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", 53.218189, 6.538995));
        gasStations.add(new GasStation("Tango Odenseweg", "Odenseweg 11", 1.619, 1.339, "October 3, 2018", 53.223004, 6.610190));
        gasStations.add(new GasStation("Tango Delfzijl", "Sikkel 32", 1.639, 1.369, "October 3, 2018", 53.321366,6.882926));
        //gasStations.add(new GasStation("Tango Hoogezand Hoofdstraat", "Hoofdstraat 47A", 1.649, 1.399, "October 3, 2018", 53.163907,6.758118));
        //gasStations.add(new GasStation("Tango Hoogezand Kuypersingel", "Abraham Kuypersingel 25", 1.649, 1.399, "October 3, 2018", 53.151962,6.756357));
        gasStations.add(new GasStation("Tango Veendam Lloydsweg", "Lloydsweg 28", 1.639, 1.379, "October 3, 2018", 53.105203,6.889030));
        //gasStations.add(new GasStation("Tango Veendam Sorghvlietlaan", "Sorghvlietlaan 11", 1.639, 1.379, "October 3, 2018", 53.095820,6.857981));
        gasStations.add(new GasStation("Tango Oude Pekela", "Raadhuislaan 107", 1.629, 1.359, "October 3, 2018", 53.107035,7.002643));
        gasStations.add(new GasStation("Tango Winschoten", "Papierbaan 3D", 1.599, 1.309, "October 3, 2018", 53.149205,7.051700));
        gasStations.add(new GasStation("Tango Stadskanaal", "Poststraat 21", 1.619, 1.369, "October 3, 2018", 52.993096,6.944214));
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

        LatLng groningen = new LatLng(53.213846,6.569568);

        for (GasStation station: gasStations) {
            mMap.addMarker(new MarkerOptions().position(station.getLocation()).title(station.getName()).snippet("Euro95: " + station.getEuro95() + ", Diesel: " + station.getDiesel()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(groningen, 12));
    }
}
