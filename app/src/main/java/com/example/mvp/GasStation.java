package com.example.mvp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class GasStation implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public GasStation createFromParcel(Parcel in) {
            return new GasStation(in);
        }

        public GasStation[] newArray(int size) {
            return new GasStation[size];
        }
    };

    private String name;
    private String address;
    private double euro95;
    private double diesel;
    private String lastUpdated;
    private double latitude;
    private double longitude;
    private LatLng location;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getChain() {
        return chain;
    }

    // Parcelling part
    public GasStation(Parcel in) {
        this.name        = in.readString();
        this.address     = in.readString();
        this.euro95      = in.readDouble();
        this.diesel      = in.readDouble();
        this.lastUpdated = in.readString();
        this.latitude    = in.readDouble();
        this.longitude   = in.readDouble();
        this.chain       = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeDouble(this.euro95);
        dest.writeDouble(this.diesel);
        dest.writeString(this.lastUpdated);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }
}
