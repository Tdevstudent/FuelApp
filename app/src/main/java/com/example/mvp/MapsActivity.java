package com.example.mvp;

import android.location.Location;
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

        gasStations.add(new GasStation("Shell Zonnelaan", "Zonnelaan 389", 1.629, 1.339, "September 26, 2018", 53.235381, 6.539334,  "Shell"));
        gasStations.add(new GasStation("BP Paddepoel", "Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", 53.229204, 6.545308,  "BP"));
        gasStations.add(new GasStation("Gulf Hoendiep", "Hoendiep 94", 1.719, 1.419, "September 26, 2018", 53.212751, 6.539237,  "Gulf"));
        gasStations.add(new GasStation("BP Paterswoldseweg", "Paterswoldseweg 139", 1.709, 1.409, "September 26, 2018", 53.203064, 6.558387,  "BP"));
        gasStations.add(new GasStation("Tamoil Express Peizerweg", "Peizerweg 9727", 1.599, 1.329, "September 26, 2018", 53.207897, 6.538240, "Tamoil"));
        gasStations.add(new GasStation("Tango Groningen Zuiderweg", "Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", 53.200634, 6.512217,  "Tango"));
        gasStations.add(new GasStation("Tango Groningen Laan", "LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", 53.218189, 6.538995,  "Tango"));
        gasStations.add(new GasStation("Tango Odenseweg", "Odenseweg 11", 1.619, 1.339, "October 3, 2018", 53.223004, 6.610190,  "Tango"));
        gasStations.add(new GasStation("Tango Delfzijl", "Sikkel 32", 1.639, 1.369, "October 3, 2018", 53.321366, 6.882926, "Tango"));
        //gasStations.add(new GasStation("Tango Hoogezand Hoofdstraat", "Hoofdstraat 47A", 1.649, 1.399, "October 3, 2018", 53.163907,6.758118, "Tango"));
        //gasStations.add(new GasStation("Tango Hoogezand Kuypersingel", "Abraham Kuypersingel 25", 1.649, 1.399, "October 3, 2018", 53.151962,6.756357, "Tango"));
        gasStations.add(new GasStation("Tango Veendam Lloydsweg", "Lloydsweg 28", 1.639, 1.379, "October 3, 2018", 53.105203, 6.889030, "Tango"));
        //gasStations.add(new GasStation("Tango Veendam Sorghvlietlaan", "Sorghvlietlaan 11", 1.639, 1.379, "October 3, 2018", 53.095820,6.857981, "Tango"));
        gasStations.add(new GasStation("Tango Oude Pekela", "Raadhuislaan 107", 1.629, 1.359, "October 3, 2018", 53.107035, 7.002643, "Tango"));
        gasStations.add(new GasStation("Tango Winschoten", "Papierbaan 3D", 1.599, 1.309, "October 3, 2018", 53.149205, 7.051700, "Tango"));
        gasStations.add(new GasStation("Tango Stadskanaal", "Poststraat 21", 1.619, 1.369, "October 3, 2018", 52.993096, 6.944214, "Tango"));

        Location zernike = new Location("");
        zernike.setLatitude(53.240475);
        zernike.setLongitude(6.536173);

        Location zonnelaan = new Location("");
        zonnelaan.setLatitude(gasStations.get(0).getLatitude());
        zonnelaan.setLongitude(gasStations.get(0).getLongitude());
        float zonneelaan_distance = zernike.distanceTo(zonnelaan) / 1000;

        Location paddepoel = new Location("");
        paddepoel.setLatitude(gasStations.get(1).getLatitude());
        paddepoel.setLongitude(gasStations.get(1).getLongitude());
        float paddepoel_distance = zernike.distanceTo(paddepoel) / 1000;

        Location hoendiep = new Location("");
        hoendiep.setLatitude(gasStations.get(2).getLatitude());
        hoendiep.setLongitude(gasStations.get(2).getLongitude());
        float hoendiep_distance = zernike.distanceTo(hoendiep) / 1000;

        Location paterswoldseweg = new Location("");
        paterswoldseweg.setLatitude(gasStations.get(3).getLatitude());
        paterswoldseweg.setLongitude(gasStations.get(3).getLongitude());
        float paterswoldseweg_distance = zernike.distanceTo(paterswoldseweg) / 1000;

        Location peizerweg = new Location("");
        peizerweg.setLatitude(gasStations.get(4).getLatitude());
        peizerweg.setLongitude(gasStations.get(4).getLongitude());
        float peizerweg_distance = zernike.distanceTo(peizerweg) / 1000;

        Location zuiderweg = new Location("");
        zuiderweg.setLatitude(gasStations.get(5).getLatitude());
        zuiderweg.setLongitude(gasStations.get(5).getLongitude());
        float zuiderweg_distance = zernike.distanceTo(zuiderweg) / 1000;

        Location laan = new Location("");
        laan.setLatitude(gasStations.get(6).getLatitude());
        laan.setLongitude(gasStations.get(6).getLongitude());
        float laan_distance = zernike.distanceTo(laan) / 1000;

        Location odenseweg = new Location("");
        odenseweg.setLatitude(gasStations.get(7).getLatitude());
        odenseweg.setLongitude(gasStations.get(7).getLongitude());
        float odenseweg_distance = zernike.distanceTo(odenseweg) / 1000;

        Location delfzijl = new Location("");
        delfzijl.setLatitude(gasStations.get(8).getLatitude());
        delfzijl.setLongitude(gasStations.get(8).getLongitude());
        float delfzijl_distance = zernike.distanceTo(delfzijl) / 1000;

        Location lloydsweg = new Location("");
        lloydsweg.setLatitude(gasStations.get(9).getLatitude());
        lloydsweg.setLongitude(gasStations.get(9).getLongitude());
        float lloydsweg_distance = zernike.distanceTo(lloydsweg) / 1000;

        Location pekela = new Location("");
        pekela.setLatitude(gasStations.get(10).getLatitude());
        pekela.setLongitude(gasStations.get(10).getLongitude());
        float pekela_distance = zernike.distanceTo(pekela) / 1000;

        Location winschoten = new Location("");
        winschoten.setLatitude(gasStations.get(11).getLatitude());
        winschoten.setLongitude(gasStations.get(11).getLongitude());
        float winschoten_distance = zernike.distanceTo(winschoten) / 1000;

        Location stadskanaal = new Location("");
        stadskanaal.setLatitude(gasStations.get(12).getLatitude());
        stadskanaal.setLongitude(gasStations.get(12).getLongitude());
        float stadskanaal_distance = zernike.distanceTo(stadskanaal) / 1000;
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
