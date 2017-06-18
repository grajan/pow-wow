package com.example.locuslabs.themesupport;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.locuslabs.sdk.configuration.LocusLabs;
import com.locuslabs.sdk.configuration.Logger;
import com.locuslabs.sdk.maps.model.AirportDatabase;
import com.locuslabs.sdk.maps.model.VenueInfo;

import java.util.Arrays;
import java.util.Comparator;

public class AirportListActivity extends Activity {
    private ListView listView;
    private Context context;
    static public AirportDatabase airportDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.initialize();
        Logger.shared.setLogLevel(Logger.LogLevelWarn);

        setContentView(R.layout.activity_airport_list);
        listView = (ListView) findViewById(R.id.listView);
        airportDatabase =  new AirportDatabase();

        context = this;

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

        LocusLabs.registerOnReadyListener(new LocusLabs.OnReadyListener() {
            @Override
            public void onReady() {
                listAirports();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VenueInfo venueInfo = (VenueInfo) parent.getItemAtPosition(position);

                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("venueId", venueInfo.getVenueId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        airportDatabase.close();
        airportDatabase = null;
    }

    private void listAirports() {
        airportDatabase.listAirports(new AirportDatabase.OnListAirportsListener() {

            @Override
            public void onListAirports(final VenueInfo[] venueInfos) {
                updateListView(venueInfos);
            }

        });
    }

    private void updateListView(final VenueInfo[] venueInfos) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Arrays.sort(venueInfos, new Comparator() {
                    public int compare(Object a, Object b) {
                        if (a != null && b != null && ((VenueInfo)a).getName() != null && ((VenueInfo)b).getName() != null) {
                            return ((VenueInfo) a).getName().compareTo(((VenueInfo) b).getName());
                        } else {
                            return -1;
                        }
                    }
                });

                ArrayAdapter<VenueInfo> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, venueInfos);
                listView.setAdapter(adapter);
            }
        });
    }
}
