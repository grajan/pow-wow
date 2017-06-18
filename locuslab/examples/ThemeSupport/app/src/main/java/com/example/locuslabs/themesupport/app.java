package com.example.locuslabs.themesupport;

import android.app.Application;

import com.locuslabs.sdk.configuration.LocusLabs;

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LocusLabs.initialize(getApplicationContext(),"A11F4Y6SZRXH4X");
    }
}
