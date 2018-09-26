package com.example.mvp;

public class GasStation {
    private String name;
    private String address;
    private double euro95;
    private double diesel;
    private String lastUpdated;
    public GasStation(String name, String address, double euro95, double diesel, String lastUpdated) {
        this.name=name;
        this.address=address;
        this.euro95=euro95;
        this.diesel=diesel;
        this.lastUpdated=lastUpdated;
    }

    public String getLastUpdated() {
        return this.lastUpdated;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEuro95() {
        return "€"+this.euro95;
    }
}
