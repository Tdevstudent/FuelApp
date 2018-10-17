package com.example.mvp;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationTracking extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    private AppCompatActivity parent_activity;
    private Location last_location;

    public LocationTracking(AppCompatActivity parent_activity) {
        this.parent_activity = parent_activity;
    }

    private void set_lcoation(Location last_loc) {
        this.last_location = last_loc;
    }

    public Location get_location() {
        access_location();
        return this.last_location;
    }

    private void access_location() {
        checkPermission();

        GoogleApiAvailability gaa = GoogleApiAvailability.getInstance();
        int errorCode = gaa.isGooglePlayServicesAvailable(parent_activity);

        if (errorCode == ConnectionResult.SUCCESS) {
            Log.d("human", "connected");
        }else {
            Log.d("human", "couldn't connect");
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        SettingsClient client = LocationServices.getSettingsClient(parent_activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(parent_activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                // All location settings are satisfied. The client can initialize
                // location requests here.
                Log.d("human", "location settings are satisfied");
                get_last_known_location();

            }
        });

        task.addOnFailureListener(parent_activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    Log.d("human", "location settings are not satisfied");
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(parent_activity,
                                REQUEST_CHECK_SETTINGS);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(parent_activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(parent_activity,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ){//Can add more as per requirement

            ActivityCompat.requestPermissions(parent_activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
    }

    private void get_last_known_location() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(parent_activity);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(parent_activity, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            Log.d("human", "Location = " + location.toString());
                            set_lcoation(location);
                        }
                    }
                });

        mFusedLocationClient.getLastLocation()
                .addOnFailureListener(parent_activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("human", "error = " + e);
                    }
                });
    }
}
