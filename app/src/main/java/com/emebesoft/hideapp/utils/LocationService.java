package com.emebesoft.hideapp.utils;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.emebesoft.hideapp.hideDetailView.HideDetailActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class LocationService implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    private LocationCallback locationListener;
    private Activity activity;
    private static final int REQUEST_CODE_PERMISSION = 2;

    public LocationService(Activity activity, LocationCallback locationListener){
        this.activity = activity;
        this.locationListener = locationListener;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //Requerimos los permisos de localización
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_PERMISSION);
        }

        locationListener.onLocated(LocationServices.FusedLocationApi.getLastLocation(HideDetailActivity.GOOGLE_API_CLIENT));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
