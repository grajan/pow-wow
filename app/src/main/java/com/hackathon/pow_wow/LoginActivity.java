package com.hackathon.pow_wow;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Muazzam on 6/17/2017.
 */

public class LoginActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}
