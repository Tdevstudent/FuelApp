package com.example.mvp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

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
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));
        gasStations.add(new GasStation("Shell","Zonnelaan 389", 1.629, 1.339, "September 26, 2018"));

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
