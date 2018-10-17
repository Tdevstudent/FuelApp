package com.example.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTab extends Fragment {

    private ArrayList<GasStation> gasStations = new ArrayList<>();
    private ListView listView;
    private static CustomAdapter adapter;
    private boolean sortedByEuro95=false;
    private static final String TAB_FRAGMENT_TAG = "LIST";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);
        gasStations = (ArrayList) getArguments().getParcelableArrayList("gasStations").clone();
        listView = view.findViewById(R.id.list);

        adapter = new CustomAdapter(gasStations, getActivity().getApplicationContext());
        listView.setAdapter(adapter);

        // TODO: make station details work
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GasStation station = gasStations.get(position);

                Intent intent = new Intent(ListTab.this, StationDetailsActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });*/

        return view;
    }
    public void refresh(String fuelType, ArrayList<GasStation> filteredGasStations) {
        if (fuelType!="empty") {
            adapter.setFuelType(fuelType);
        }
        if (filteredGasStations!=null) {
            gasStations.clear();
            ArrayList<GasStation> cloneStations=(ArrayList)filteredGasStations.clone();
            for (int i=0; i<cloneStations.size(); i++) {
                gasStations.add(cloneStations.get(i));
            }
            Log.d("KLAAS", Integer.toString(filteredGasStations.size()));
        }
        adapter.notifyDataSetChanged();
    }


}
