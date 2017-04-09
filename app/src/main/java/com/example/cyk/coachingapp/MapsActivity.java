package com.example.cyk.coachingapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker m;

    private Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        validateButton = (Button) findViewById(R.id.button);
        validateButton.setEnabled(false);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle mBundle = new Bundle();
                if(m!=null) {
                    mBundle.putDouble("X", m.getPosition().latitude);
                    mBundle.putDouble("Y", m.getPosition().longitude);
                }
                Intent intent = new Intent(MapsActivity.this, MatchActivity.class).putExtras(mBundle);
                startActivity(intent);
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng position) {
                validateButton.setEnabled(true);
                if (m != null) { //if marker exists (not null or whatever)
                    m.setPosition(position);
                } else {
                    m = mMap.addMarker(new MarkerOptions()
                            .position(position));
                }
            }
        });
    }
}
