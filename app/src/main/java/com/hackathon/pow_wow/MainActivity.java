package com.hackathon.pow_wow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method invoked after user logged in
     * @param view the button which the user tapped
     */
    public void onLogin(View view) {

        Intent meetUpIntent = new Intent(this, MeetUpActivity.class);
        startActivity(meetUpIntent);

    }
}
