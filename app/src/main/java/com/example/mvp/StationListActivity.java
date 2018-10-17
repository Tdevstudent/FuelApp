package com.example.mvp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toolbar;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StationListActivity extends ListActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<GasStation> gasStations=new ArrayList<>();
    private ListView listView;
    private static CustomAdapter adapter;
    private boolean sortedByEuro95=false;
    private LocationTracking loc_tracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_main);
        Log.d("human", "list activity created");

        listView = findViewById(R.id.list);

        gasStations.add(new GasStation("Shell", "Zonnelaan 389", 1.629, 1.339, "September 26, 2018", 53.235381, 6.539334));
        gasStations.add(new GasStation("BP Paddepoel", "Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", 53.229204, 6.545308));
        gasStations.add(new GasStation("Gulf", "Hoendiep 94", 1.719, 1.419, "September 26, 2018", 53.212751, 6.539237));
        gasStations.add(new GasStation("BP", "Paterswoldsweg 139", 1.709, 1.409, "September 26, 2018", 53.203064, 6.558387));
        gasStations.add(new GasStation("Tamoil Express", "Peizerweg 9727", 1.599, 1.329, "September 26, 2018", 53.207897, 6.538240));
        gasStations.add(new GasStation("Tango Groningen Zuiderweg", "Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", 53.200634, 6.512217));
        gasStations.add(new GasStation("Tango Groningen Laan", "LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", 53.218189, 6.538995));
        gasStations.add(new GasStation("Tango Odenseweg", "Odenseweg 11", 1.619, 1.339, "October 3, 2018", 53.223004, 6.610190));
        gasStations.add(new GasStation("Tango Delfzijl", "Sikkel 32", 1.639, 1.369, "October 3, 2018", 53.321366, 6.882926));
        //gasStations.add(new GasStation("Tango Hoogezand Hoofdstraat", "Hoofdstraat 47A", 1.649, 1.399, "October 3, 2018", 53.163907,6.758118));
        //gasStations.add(new GasStation("Tango Hoogezand Kuypersingel", "Abraham Kuypersingel 25", 1.649, 1.399, "October 3, 2018", 53.151962,6.756357));
        gasStations.add(new GasStation("Tango Veendam Lloydsweg", "Lloydsweg 28", 1.639, 1.379, "October 3, 2018", 53.105203, 6.889030));
        //gasStations.add(new GasStation("Tango Veendam Sorghvlietlaan", "Sorghvlietlaan 11", 1.639, 1.379, "October 3, 2018", 53.095820,6.857981));
        gasStations.add(new GasStation("Tango Oude Pekela", "Raadhuislaan 107", 1.629, 1.359, "October 3, 2018", 53.107035, 7.002643));
        gasStations.add(new GasStation("Tango Winschoten", "Papierbaan 3D", 1.599, 1.309, "October 3, 2018", 53.149205, 7.051700));
        gasStations.add(new GasStation("Tango Stadskanaal", "Poststraat 21", 1.619, 1.369, "October 3, 2018", 52.993096, 6.944214));

        adapter = new CustomAdapter(gasStations, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GasStation station = gasStations.get(position);

                /*Snackbar.make(view, station.getName()+", "+station.getAddress()+"\n"+station.getEuro95()+" Last updated "+station.getLastUpdated(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/

                Intent intent = new Intent(StationListActivity.this, StationDetailsActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });

        this.loc_tracking = new LocationTracking(this);
        loc_tracking.get_location();

        // Navigation drawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        Spinner spinner = (Spinner) navigationView.getMenu().findItem(R.id.navigation_drawer_item1).getActionView();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fuel_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        Switch fuelTypeSwitch = (Switch) menu.findItem(R.id.switchItem).getActionView().findViewById(R.id.fuelTypeSwitch);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.prices:
                if (!sortedByEuro95) {
                    Collections.sort(gasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o1.getEuro95().compareTo(o2.getEuro95());
                        }
                    });
                    sortedByEuro95=true;
                } else {
                    Collections.sort(gasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o2.getEuro95().compareTo(o1.getEuro95());
                        }
                    });
                    sortedByEuro95=false;
                }
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        adapter.setFuelType((String)parent.getItemAtPosition(pos));
        adapter.notifyDataSetChanged();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}