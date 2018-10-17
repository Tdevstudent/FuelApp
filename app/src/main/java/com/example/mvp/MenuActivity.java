package com.example.mvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LocationTracking loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("human", "activity created");

        setContentView(R.layout.activity_menu);
        this.loc = new LocationTracking(this);
        loc.access_location();
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("human", "activity resumed");

        loc.access_location();
    }

    public void openMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openList(View view) {
        Intent intent = new Intent(this, StationListActivity.class);
        startActivity(intent);
    }

    public void openTabs(View view) {
        Intent intent = new Intent(this, TabbedActivity.class);
        startActivity(intent);
    }
}
