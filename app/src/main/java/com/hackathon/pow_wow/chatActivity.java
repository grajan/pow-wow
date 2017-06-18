package com.hackathon.pow_wow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Muazzam on 6/18/2017.
 */

public class chatActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
