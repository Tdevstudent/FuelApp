package com.example.mvp;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<GasStation> gasStations = new ArrayList<>();
    private ArrayList<GasStation> filteredGasStations=new ArrayList<>();
    private ArrayList<String> hiddenStations=new ArrayList<String>();
    private DrawerLayout mDrawerLayout;
    private ListTab listFragment;
    private MapTab menuFragment;
    private boolean sortedByPrice=false;
    private boolean sortedByName=false;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        listFragment = (ListTab) getSupportFragmentManager().findFragmentByTag("LIST");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        NavigationView navigationView = findViewById(R.id.nav_view);

        createGasStations();

        setNavigationDrawer(navigationView);

        setCheckBoxes(navigationView);

        makeGasStationsList(gasStations);
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
                listFragment.refresh("empty", filteredGasStations);
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

    public void setNavigationDrawer(NavigationView navigationView) {
        // Navigation drawer
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

    public void createGasStations() {
        gasStations.add(new GasStation("Shell Zonnelaan", "Zonnelaan 389", 1.629, 1.339, "September 26, 2018", 53.235381, 6.539334, "Shell"));
        gasStations.add(new GasStation("BP Paddepoel", "Pleiadenlaan 9742", 1.669, 1.379, "September 26, 2018", 53.229204, 6.545308, "BP"));
        gasStations.add(new GasStation("Gulf Hoendiep", "Hoendiep 94", 1.719, 1.419, "September 26, 2018", 53.212751, 6.539237, "Gulf"));

        gasStations.add(new GasStation("BP Paterswoldseweg", "Paterswoldseweg 139", 1.709, 1.409, "September 26, 2018", 53.203064, 6.558387, "BP"));

        gasStations.add(new GasStation("Tamoil Express Peizerweg", "Peizerweg 9727", 1.599, 1.329, "September 26, 2018", 53.207897, 6.538240, "Tamoil"));

        gasStations.add(new GasStation("Tango Groningen Zuiderweg", "Zuiderweg 409a", 1.579, 1.269, "September 26, 2018", 53.200634, 6.512217, "Tango"));
        gasStations.add(new GasStation("Tango Groningen Laan", "LAAN 1940-1945 nr 300", 1.579, 1.269, "September 26, 2018", 53.218189, 6.538995, "Tango"));
        gasStations.add(new GasStation("Tango Odenseweg", "Odenseweg 11", 1.619, 1.339, "October 3, 2018", 53.223004, 6.610190, "Tango"));
        gasStations.add(new GasStation("Tango Delfzijl", "Sikkel 32", 1.639, 1.369, "October 3, 2018", 53.321366, 6.882926, "Tango"));
        //gasStations.add(new GasStation("Tango Hoogezand Hoofdstraat", "Hoofdstraat 47A", 1.649, 1.399, "October 3, 2018", 53.163907,6.758118, "Tango"));
        //gasStations.add(new GasStation("Tango Hoogezand Kuypersingel", "Abraham Kuypersingel 25", 1.649, 1.399, "October 3, 2018", 53.151962,6.756357, "Tango"));
        gasStations.add(new GasStation("Tango Veendam Lloydsweg", "Lloydsweg 28", 1.639, 1.379, "October 3, 2018", 53.105203, 6.889030, "Tango"));
        //gasStations.add(new GasStation("Tango Veendam Sorghvlietlaan", "Sorghvlietlaan 11", 1.639, 1.379, "October 3, 2018", 53.095820,6.857981, "Tango"));
        gasStations.add(new GasStation("Tango Oude Pekela", "Raadhuislaan 107", 1.629, 1.359, "October 3, 2018", 53.107035, 7.002643, "Tango"));
        gasStations.add(new GasStation("Tango Winschoten", "Papierbaan 3D", 1.599, 1.309, "October 3, 2018", 53.149205, 7.051700, "Tango"));
        gasStations.add(new GasStation("Tango Stadskanaal", "Poststraat 21", 1.619, 1.369, "October 3, 2018", 52.993096, 6.944214, "Tango"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.sort_price:
                sortedByName=false;
                if (!sortedByPrice) {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o1.getEuro95().compareTo(o2.getEuro95());
                        }
                    });
                    sortedByPrice=true;
                } else {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o2.getEuro95().compareTo(o1.getEuro95());
                        }
                    });
                    sortedByPrice=false;
                }
                listFragment.refresh("empty", filteredGasStations);
                return true;
            case R.id.sort_name:
                sortedByPrice=false;
                if (!sortedByName) {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    sortedByName=true;
                } else {
                    Collections.sort(filteredGasStations, new Comparator<GasStation>() {
                        @Override
                        public int compare(GasStation o1, GasStation o2) {
                            return o2.getName().compareTo(o1.getName());
                        }
                    });
                    sortedByName=false;
                }
                listFragment.refresh("empty", filteredGasStations);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("gasStations", gasStations);
            switch (position) {
                case 0:
                    listFragment = new ListTab();
                    listFragment.setArguments(bundle);
                    return listFragment;
                case 1:
                    MapTab mapTab = new MapTab();
                    mapTab.setArguments(bundle);
                    return mapTab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LIST";
                case 1:
                    return "MAPS";
            }
            return null;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        //adapter.setFuelType((String)parent.getItemAtPosition(pos));
        //adapter.notifyDataSetChanged();
        listFragment.refresh((String)parent.getItemAtPosition(pos), filteredGasStations);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
