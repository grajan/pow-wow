package com.example.locuslabs.themesupport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.locuslabs.sdk.configuration.Logger;
import com.locuslabs.sdk.maps.model.Airport;
import com.locuslabs.sdk.maps.model.AirportDatabase;
import com.locuslabs.sdk.maps.model.Floor;
import com.locuslabs.sdk.maps.model.Map;
import com.locuslabs.sdk.maps.model.Marker;
import com.locuslabs.sdk.maps.model.Theme;
import com.locuslabs.sdk.maps.view.MapView;

/*
* This activity loads a map then shows a POI View with an Id of
* 126 with modified user interface elements. The utilized Typeface,
* TextSize, Color, and background Color are manipulated programmatically
* via the ThemeBuilder, Theme, and MapView.
* */
public class MapActivity extends Activity {

    private AirportDatabase mAirportDatabase;
    private MapView mMapView;
    private Context mContext;
    private Airport mAirport;

    //==========================//
    // Native Android Lifecycle //
    //==========================//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This activity takes a venueId parameter. The venueId represents the Airport to be loaded.
        Intent receivedIntent = getIntent();
        String venueId = receivedIntent.getStringExtra("venueId");

        //Create an AirportDatabase which allows airports to be loaded.
        mAirportDatabase = new AirportDatabase();

        mContext = getApplicationContext();

        //Load the Airport specified by the venueId passed to the activity.
        loadAirportAndMap(venueId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //-----------------------------------
        // Be sure to close the mMapView and
        // mAirportDatabase to release the memory
        // they consume.
        //-----------------------------------

        if (mAirportDatabase != null) {
            mAirportDatabase.close();
            mAirportDatabase = null;
        }

        if (mMapView != null) {
            mMapView.close();
            mMapView = null;
        }

    }

    //==============================//
    //     Staggered (Fast) Load    //
    //==============================//

    private void loadAirportAndMap(String venueId) {
        final RelativeLayout rl = new RelativeLayout(mContext);

        AirportDatabase.OnLoadAirportAndMapListeners listeners = new AirportDatabase.OnLoadAirportAndMapListeners();
        listeners.loadedInitialViewListener = new AirportDatabase.OnLoadedInitialViewListener() {
            @Override public void onLoadedInitialView(View view) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) parent.removeView(view);

                view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                rl.addView(view);
                setContentView(rl);
            }
        };
        listeners.loadCompletedListener = new AirportDatabase.OnLoadCompletedListener() {

            @Override public void onLoadCompleted(Airport _airport, Map _map, final MapView _mapView, Floor floor, Marker marker) {
                mAirport = _airport;
                mMapView = _mapView;
                // Note: Themes should be added before the MapView is ready. This will prevent the
                // undesirable and noticeable visual changes to the Presentation Layer for your application
                // when overriding the default user interface.
                setMapViewTheme(_mapView);

                attachFunctionalityToMapView(_mapView);
            }
        };
        listeners.loadFailedListener = new AirportDatabase.OnLoadFailedListener() {
            @Override
            public void onLoadFailed(String exceptionMessage) {}
        };

        listeners.loadProgressListener = new AirportDatabase.OnLoadProgressListener() {
            @Override
            public void onLoadProgress(Integer percentComplete) {}
        };

        mAirportDatabase.loadAirportAndMap(venueId, null, listeners);
    }

    //=================================//
    // onLoad Airport/MapView Handlers //
    //=================================//

    private void attachFunctionalityToMapView(final MapView mapView) {

        mapView.setOnReadyListener(new MapView.OnReadyListener() {
            @Override
            public void onReady() {
                // The MapView callback onReady
                Logger.info("Ready");

                //  Default API Expected in Most Client Applications, expected in MapView's onReady callback.
                trySupplyCurrentActivity();

                // Let's take a look at our new Theme for the POI ViewGroup!
                mapView.showPoiPopup("126");
            }
        });

    }


    //=============//
    //  Theme APIs //
    //=============//

    private void setMapViewTheme(MapView mapView) {

        Typeface typeface_a = Typeface.create(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
        Typeface typeface_b = Typeface.create(Typeface.MONOSPACE,Typeface.NORMAL);
        Typeface typeface_c = Typeface.create(Typeface.SERIF,Typeface.BOLD_ITALIC);


        Theme theme = mMapView
                .themeBuilder()
                .setProperty("view.poi.color.background", Color.DKGRAY)
                // POI Icon
                .setProperty("view.poi.icon.color.tint",Color.WHITE)
                .setProperty("view.poi.icon.color.background",Color.TRANSPARENT)
                // POI Header
                .setProperty("view.poi.header.color.background",Color.TRANSPARENT)
                // POI Header Icon
                .setProperty("view.poi.header.icon.color.tint",Color.WHITE)
                .setProperty("view.poi.header.icon.color.background",Color.TRANSPARENT)
                // POI Header Title
                .setProperty("view.poi.header.title.color.text",Color.WHITE)
                .setProperty("view.poi.header.title.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.header.title.font.name", typeface_a)
                .setProperty("view.poi.header.title.font.size", 20.0)
                // POI Header Location
                .setProperty("view.poi.header.location.color.text",Color.WHITE)
                .setProperty("view.poi.header.location.color.tint",Color.WHITE)
                .setProperty("view.poi.header.location.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.header.location.font.name", typeface_b)
                .setProperty("view.poi.header.location.font.size", 16.0)
                // POI Header Security
                .setProperty("view.poi.header.security.color.text",Color.WHITE)
                .setProperty("view.poi.header.security.color.tint",Color.WHITE)
                .setProperty("view.poi.header.security.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.header.security.font.name", typeface_b)
                .setProperty("view.poi.header.security.font.size", 16.0)
                // POI Header Level
                .setProperty("view.poi.header.level.color.text",Color.WHITE)
                .setProperty("view.poi.header.level.color.tint",Color.WHITE)
                .setProperty("view.poi.header.level.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.header.level.font.name", typeface_b)
                .setProperty("view.poi.header.level.font.size", 16.0)
                // POI Carousel
                .setProperty("view.poi.carousel.color.background",Color.DKGRAY)
                .setProperty("view.poi.carousel.error.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.carousel.loader.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.carousel.image.color.background",Color.LTGRAY)
                // POI Carousel - Label
                .setProperty("view.poi.carousel.label.color.text",Color.BLACK)
                .setProperty("view.poi.carousel.label.color.background",Color.WHITE)
                .setProperty("view.poi.carousel.label.font.name", typeface_b)
                .setProperty("view.poi.carousel.label.font.size", 12.0)
                // POI Carousel - Loading
                .setProperty("view.poi.carousel.loading.color.background", Color.TRANSPARENT)
                .setProperty("view.poi.carousel.loading.text.color.text", Color.LTGRAY)
                .setProperty("view.poi.carousel.loading.text.color.background", Color.TRANSPARENT)
                .setProperty("view.poi.carousel.loading.text.font.name", typeface_b)
                .setProperty("view.poi.carousel.loading.text.font.size", 12.0)
                // POI Details Description String
                .setProperty("view.poi.detail.description.color.text",Color.WHITE)
                .setProperty("view.poi.detail.description.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.detail.description.font.name", typeface_b)
                .setProperty("view.poi.detail.description.font.size", 16.0)
                // POI Details Spannable Tag String
                .setProperty("view.poi.detail.tag.color.text",Color.BLACK)
                .setProperty("view.poi.detail.tag.color.background",Color.WHITE)
                // POI Contact - Website
                .setProperty("view.poi.contact.website.active.color.text",Color.YELLOW)
                .setProperty("view.poi.contact.website.active.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.contact.website.active.font.name", typeface_b)
                .setProperty("view.poi.contact.website.active.font.size", 18.0)
                .setProperty("view.poi.contact.website.default.color.text",Color.WHITE)
                .setProperty("view.poi.contact.website.default.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.contact.website.default.font.name", typeface_b)
                .setProperty("view.poi.contact.website.default.font.size", 16.0)
                // POI Contact - Phone
                .setProperty("view.poi.contact.phone.active.color.text",Color.YELLOW)
                .setProperty("view.poi.contact.phone.active.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.contact.phone.active.font.name", typeface_b)
                .setProperty("view.poi.contact.phone.active.font.size", 14.0)
                .setProperty("view.poi.contact.phone.default.color.text",Color.WHITE)
                .setProperty("view.poi.contact.phone.default.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.contact.phone.default.font.name", typeface_b)
                .setProperty("view.poi.contact.phone.default.font.size", 16.0)
                // POI Buttons
                .setProperty("view.poi.button.color.text",Color.WHITE)
                .setProperty("view.poi.button.color.tint",Color.WHITE)
                .setProperty("view.poi.button.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.button.font.name", typeface_b)
                .setProperty("view.poi.button.font.size", 13.0)
                // POI Operational Times - Day
                .setProperty("view.poi.time.day.color.text",Color.WHITE)
                .setProperty("view.poi.time.day.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.time.day.font.name", typeface_a)
                .setProperty("view.poi.time.day.font.size", 16.0)
                // POI Operational Times - compress Hour
                .setProperty("view.poi.time.hours.color.text",Color.LTGRAY)
                .setProperty("view.poi.time.hours.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.time.hours.font.name", typeface_c)
                .setProperty("view.poi.time.hours.font.size", 18.0)

                // POI Operational Times - extended Hour
                .setProperty("view.poi.time.hour.color.text",Color.LTGRAY)
                .setProperty("view.poi.time.hour.color.background",Color.TRANSPARENT)
                .setProperty("view.poi.time.hour.font.name", typeface_c)
                .setProperty("view.poi.time.hour.font.size", 18.0)
                // Create DefaultTheme
                .createTheme();

        mMapView.setTheme(theme);
    }

    //====================================================//
    //  Default API Expected in Most Client Applications //
    //====================================================//

    //This allows activities to be started by providing the LocusLabs SDK with the current activity when a new activity is started.
    private void trySupplyCurrentActivity(){
        // Provides the current activity to the mMapView when a callback is requested.
        mMapView.setOnSupplyCurrentActivityListener(new MapView.OnSupplyCurrentActivityListener() {
            @Override
            public Activity onSupplyCurrentActivity() {
                return MapActivity.this;
            }
        });
    }
}
