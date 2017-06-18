package com.example.locuslabs.recommendedimplementation;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

public class FlightCardActivity extends Activity {
    private static final String TAG = "FlightCardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // MY_ID is an app-defined int constant. The callback method gets the result of the request.
            int MY_ID = 1;
            requestPermissions(new String[]{
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CALL_PHONE
            }, MY_ID);
        }

        // Sets the content view to match ./res/layout/flight_card.xml
        setContentView(R.layout.flight_card);
    }
}
