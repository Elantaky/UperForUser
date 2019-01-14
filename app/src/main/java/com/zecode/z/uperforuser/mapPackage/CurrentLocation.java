package com.zecode.z.uperforuser.mapPackage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class CurrentLocation {
/*    //TextView tvKnownName;
    String address;
    String city;
    String state;
    String country;
    String postalCode;
    String knownName;
    FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    return;
                }
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null){
                            showAddressBasedOnLocation(location);

                        }else if (location==null){
                            Toast.makeText(MainActivity.this,"wait while searching were are you bro..",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });



    }
    void calling(){

        btnGetLocation = findViewById(R.id.btnGetLocation);
        btnShowWhereImInTheMap=findViewById(R.id.btnShowWhereImInTheMap);
        tvAddress = findViewById(R.id.tvLocation);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvPostalCode = findViewById(R.id.tvPostalCode);
        tvLongitudeLatitude =findViewById(R.id.tvLongitudeLatitude);
        //tvKnownName = findViewById(R.id.tvKnownName);
    }
    void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    private void showAddressBasedOnLocation(final Location location) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city = addresses.get(0).getLocality();
            state = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            postalCode = addresses.get(0).getPostalCode();
            knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
        } catch (IOException e) {
            e.printStackTrace();
        }

        tvAddress.setText(address);
        tvCity.setText(city);
        tvState.setText(state);
        tvCountry.setText(country);
        tvPostalCode.setText(postalCode);
        tvLongitudeLatitude.setText(location.toString());
        //tvKnownName.setText(knownName);
        btnShowWhereImInTheMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri theLocationURI = Uri.parse("google.streetview:cbll="+location.toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, theLocationURI);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }*/
}
