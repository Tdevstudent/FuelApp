package com.example.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_main, container, false);
        gasStations = getArguments().getParcelableArrayList("gasStations");

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
}
