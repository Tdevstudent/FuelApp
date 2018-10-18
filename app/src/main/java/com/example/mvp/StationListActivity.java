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
import android.widget.CheckBox;
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
    private ArrayList<GasStation> filteredGasStations=new ArrayList<>();
    private ListView listView;
    private static CustomAdapter adapter;
    private boolean sortedByEuro95=false;
    private LocationTracking loc_tracking;
    private ArrayList<String> hiddenStations=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list_main);
        Log.d("human", "list activity created");

        listView = findViewById(R.id.list);

        this.loc_tracking = new LocationTracking(this);
        loc_tracking.get_location();

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

        filteredGasStations = (ArrayList) gasStations.clone();

        adapter = new CustomAdapter(filteredGasStations, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                GasStation station = filteredGasStations.get(position);

                /*Snackbar.make(view, station.getName()+", "+station.getAddress()+"\n"+station.getEuro95()+" Last updated "+station.getLastUpdated(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/

                Intent intent = new Intent(StationListActivity.this, StationDetailsActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });

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

        setCheckBoxes(navigationView);
    }

    public void setCheckBoxes(NavigationView navigationView) {
        CheckBox checkBox1 = (CheckBox) navigationView.getMenu().findItem(R.id.navigation_drawer_checkbox1).getActionView();
        CheckBox checkBox2 = (CheckBox) navigationView.getMenu().findItem(R.id.navigation_drawer_checkbox2).getActionView();
        CheckBox checkBox3 = (CheckBox) navigationView.getMenu().findItem(R.id.navigation_drawer_checkbox3).getActionView();
        CheckBox checkBox4 = (CheckBox) navigationView.getMenu().findItem(R.id.navigation_drawer_checkbox4).getActionView();
        CheckBox checkBox5 = (CheckBox) navigationView.getMenu().findItem(R.id.navigation_drawer_checkbox5).getActionView();

        setCheckBox(checkBox1, "BP");
        setCheckBox(checkBox2,"Shell");
        setCheckBox(checkBox3,"Tamoil");
        setCheckBox(checkBox4,"Tango");
        setCheckBox(checkBox5,"Gulf");
    }

    public void setCheckBox(CheckBox cb, String text) {
        cb.setText(text);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                CheckBox cb=(CheckBox) buttonView;
                if (!isChecked) {
                    addHiddenStation((String)cb.getText());
                    //adapter.notifyDataSetChanged();
                } else {
                    removeHiddenStation((String)cb.getText());
                }
                makeGasStationsList(gasStations);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void makeGasStationsList(ArrayList<GasStation> gasStationsI) {
        int count=0;
        this.filteredGasStations.clear();
        for (int i=0; i<gasStationsI.size(); i++) {
            for (int j=0; j<hiddenStations.size(); j++) {
                if (!gasStationsI.get(i).getChain().equals(hiddenStations.get(j))) {
                    count=count+1;
                }
            }
            if (count==hiddenStations.size()) {
                this.filteredGasStations.add(gasStationsI.get(i));
            }
            count=0;
        }
    }

    public void addHiddenStation(String station) {
        hiddenStations.add(station);
    }

    public void removeHiddenStation(String station) {
        for (int i=0; i<hiddenStations.size(); i++) {
            if (station.equals(hiddenStations.get(i))) {
                hiddenStations.remove(i);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.prices:
                if (!sortedByEuro95) {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o1.getEuro95().compareTo(o2.getEuro95());
                        }
                    });
                    sortedByEuro95=true;
                } else {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
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