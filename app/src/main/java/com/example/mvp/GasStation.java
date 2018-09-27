package com.example.mvp;

import com.google.android.gms.maps.model.LatLng;

public class GasStation {

    private String name;
    private String address;
    private double euro95;
    private double diesel;
    private String lastUpdated;
    private LatLng location;

    public GasStation(String name, String address, double euro95, double diesel, String lastUpdated, LatLng location) {
        this.name        = name;
        this.address     = address;
        this.euro95      = euro95;
        this.diesel      = diesel;
        this.lastUpdated = lastUpdated;
        this.location    = location;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEuro95() {
        return "€ "+this.euro95;
    }

    public String getDiesel() {
        return "€ "+this.diesel;
    }

    public String getName() {
        return this.name;
    }

    public LatLng getLocation() {
        return location;
    }
}
