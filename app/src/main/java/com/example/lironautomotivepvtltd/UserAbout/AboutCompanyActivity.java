package com.example.lironautomotivepvtltd.UserAbout;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;


import com.example.lironautomotivepvtltd.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutCompanyActivity extends FragmentActivity implements OnMapReadyCallback {

   GoogleMap mapAPI;
   SupportMapFragment supportMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_company);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map11);

        supportMapFragment.getMapAsync(this);
//
//        MapCompanyFragment mapCompanyFragment = new MapCompanyFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.fragment_map11,mapCompanyFragment).commit();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapAPI = googleMap;
//        LatLngBounds Liron = new LatLngBounds(new LatLng(18.638745,73.830674),new LatLng(18.638745,73.830674));
        LatLng location = new LatLng(18.632499,73.842014);
//        -38.075500,145.571136
        mapAPI.addMarker(new MarkerOptions().position(location).title("Liron Automotive Pvt. Ltd."));


//        mapAPI.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.638745,73.830674),14));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(location));
//        mapAPI.setMyLocationEnabled(true);
//        mapAPI.setLatLngBoundsForCameraTarget();
    }


}
