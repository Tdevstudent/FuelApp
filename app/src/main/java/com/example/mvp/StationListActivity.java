package com.example.mvp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class StationListActivity extends ListActivity {

    private ArrayList<GasStation> gasStations=new ArrayList<>();
    private ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_main);

        listView = findViewById(R.id.list);
        System.out.println(listView);
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018", new LatLng(53.235381, 6.539334)));
        gasStations.add(new GasStation("BP Paddepoel","Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", new LatLng(53.229204, 6.545308)));
        gasStations.add(new GasStation("Tango Groningen Laan","LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", new LatLng(53.218189, 6.538995)));
        gasStations.add(new GasStation("Gulf","Hoendiep 94", 1.719, 1.419, "September 26, 2018", new LatLng(53.212751, 6.539237)));
        gasStations.add(new GasStation("BP","Paterswoldsweg 139", 1.709, 1.409, "September 26, 2018", new LatLng(53.203064, 6.558387)));
        gasStations.add(new GasStation("Tamoil Express","Peizerweg 9727", 1.599, 1.329, "September 26, 2018", new LatLng(53.207897, 6.538240)));
        gasStations.add(new GasStation("Tango Groningen Zuiderweg","Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", new LatLng(53.200634, 6.512217)));

        adapter=new CustomAdapter(gasStations, getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GasStation station= gasStations.get(position);

                Snackbar.make(view, station.getAddress()+"\n"+station.getEuro95()+" API: "+station.getLastUpdated(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }
}
