package com.hackathon.pow_wow;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends FragmentActivity implements LoginFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method invoked after user logged in
     * @param view the button which the user tapped
     */
    @Override
    public void loginButtonClicked() {

        Intent meetUpIntent = new Intent(this, MeetUpActivity.class);
        startActivity(meetUpIntent);

    }
}
