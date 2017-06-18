package com.example.locuslabs.recommendedimplementation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.locuslabs.sdk.maps.model.Airport;
import com.locuslabs.sdk.maps.model.AirportDatabase;
import com.locuslabs.sdk.maps.model.Beacon;
import com.locuslabs.sdk.maps.model.Floor;
import com.locuslabs.sdk.maps.model.Map;
import com.locuslabs.sdk.maps.model.Marker;
import com.locuslabs.sdk.maps.model.Position;
import com.locuslabs.sdk.maps.model.UserPositionManager;
import com.locuslabs.sdk.maps.view.MapView;

/**
 * Sample Activity that will render the specified venue.
 */
public class MapActivity extends Activity {

    private Airport airport = null;
    private MapView mapView = null;
    private AirportDatabase airportDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receivedIntent = getIntent();
        String venueId = receivedIntent.getStringExtra("venueId");

        //Create an AirportDatabase which allows airports to be loaded.
        airportDatabase = new AirportDatabase();

        //Load the Airport specified by the venueId passed to the activity.
        loadAirport(venueId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //-----------------------------------
        // Be sure to close the mapView and
        // airportDatabase to release the memory
        // they consume.
        //-----------------------------------

        if ( mapView != null ) {
            mapView.close();
        }

        if ( airportDatabase != null ) {
            airportDatabase.close();
        }

        airportDatabase = null;
        mapView = null;
    }

    @Override
    public void onBackPressed() {
        if ( mapView == null || !mapView.onBackPressed() ) {
            super.onBackPressed();
        }
    }

    private void loadAirport(final String venueId) {
        final RelativeLayout rl = new RelativeLayout(this);

        AirportDatabase.OnLoadAirportAndMapListeners listeners = new AirportDatabase.OnLoadAirportAndMapListeners();
        listeners.loadedInitialViewListener = new AirportDatabase.OnLoadedInitialViewListener() {
            @Override
            public void onLoadedInitialView(View view) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }

                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                rl.addView(view);
                setContentView(rl);
            }
        };
        listeners.loadCompletedListener = new AirportDatabase.OnLoadCompletedListener() {
            @Override
            public void onLoadCompleted(Airport _airport, Map _map, final MapView _mapView, Floor floor, Marker marker) {
                airport = _airport;
                mapView = _mapView;

                // turn on positioning
                mapView.setPositioningEnabled(airport, true);

                UserPositionManager userPositionManager = new UserPositionManager(getApplicationContext());

                userPositionManager.registerOnPositionChangedListener( new UserPositionManager.OnPositionChangedListener() {
                    public void onPositionChanged(Position position) {
                        Log.d("onPositionChanged", position.toString());
                    }
                });

                userPositionManager.registerOnClosestBeaconChangedListener( new UserPositionManager.OnClosestBeaconChangedListener() {
                    @Override
                    public void onClosestBeaconChanged(Beacon beacon) {
                        Log.d("onClosestBeaconChanged", beacon.toString());
                    }
                });

                mapView.setOnSupplyCurrentActivityListener(new MapView.OnSupplyCurrentActivityListener() {
                    @Override
                    public Activity onSupplyCurrentActivity() {
                        return MapActivity.this;
                    }
                });
            }
        };

        // The second parameter is an initial search option.
        // The map will zoom to the first matched POI.
        airportDatabase.loadAirportAndMap(venueId, "gate:a5", listeners);
    }
}
