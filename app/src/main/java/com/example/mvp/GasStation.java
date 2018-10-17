package com.example.mvp;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class GasStation implements Serializable {

    private String name;
    private String address;
    private double euro95;
    private double diesel;
    private String lastUpdated;
    private double latitude;
    private double longitude;
    private transient LatLng location;
    private String chain;

    public GasStation(String name, String address, double euro95, double diesel, String lastUpdated, double latitude, double longitude, String chain) {
        this.name        = name;
        this.address     = address;
        this.euro95      = euro95;
        this.diesel      = diesel;
        this.lastUpdated = lastUpdated;
        this.latitude    = latitude;
        this.longitude   = longitude;
        this.location    = new LatLng(latitude, longitude);
        this.chain       = chain;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getChain() {
        return chain;
    }
}
