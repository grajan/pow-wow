package com.hackathon.pow_wow;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.hackathon.pow_wow.UDP.UDPManager;
import com.hendraanggrian.widget.SocialTextView;

import java.util.Random;

import static java.security.AccessController.getContext;

public class MeetUpActivity extends AppCompatActivity {

    private GridView tablesGridView;

    private ListView tablesListView;

    private TablesAdapter tablesAdapter;

    public static Table[] tables;

    public static int position;

    // The following are used for the shake detection
     /* put this into your activity class */
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private SensorEventListener mSensorListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        UDPManager.startServer();
        UDPManager.startClient();


        setContentView(R.layout.activity_meet_up);

        fetchTablesArray();

        tablesListView  = (ListView) findViewById(R.id.tablesListView);
        tablesAdapter   = new TablesAdapter(this, tables);
        tablesListView.setAdapter(tablesAdapter);

        tablesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                gotoDetailView(position);
            }
        });

        configureShake();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_name) {
            confirmDialog();
        }
        else if (id == R.id.action_search) {
            searchDialog();
        }
        return super.onOptionsItemSelected(item);
    }


    private void gotoDetailView(int position) {

        MeetUpActivity.position = position;
        Intent tableIntent = new Intent(MeetUpActivity.this, TableActivity.class);
        startActivity(tableIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void configureShake() {

        mSensorListener = new SensorEventListener() {

            public void onSensorChanged(SensorEvent se) {

                float x = se.values[0];
                float y = se.values[1];
                float z = se.values[2];
                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta; // perform low-cut filter

                if (mAccel > 12) {

                    Random rand = new Random();
                    int r = rand.nextInt(tables.length);
                    Log.i("mAccel", "onSensorChanged: mAccel:"+mAccel+" r:"+r);
                    Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
                    toast.show();
                    gotoDetailView(r);
                }
            }

            public void onAccuracyChanged(Sensor sensor, int accuracy) {

                if (mAccel > 12) {
                    Log.i("mAccel", "onAccuracyChanged: "+mAccel);
                    Toast toast = Toast.makeText(getApplicationContext(), "Device has shaken.", Toast.LENGTH_LONG);
                    toast.show();
                }
                Log.i("OnSensor", "onAccuracyChanged: ");
            }
        };

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    private void fetchTablesArray() {

        tables = new Table[5];

        tables[0] = new Table(R.drawable.mcdonalds_icon, R.drawable.mcdonalds_entry, R.drawable.mcdonalds_detail, "Get together @McD", "Guys, stop by at @mcdonalds, we are talking about the upcoming #gameofthrones season..","15min",3);
        tables[1] = new Table(R.drawable.custom_table_icon, R.drawable.p1, R.drawable.p1, "Head to @burgerKing for if you're a #gaming freak.", "#momentOfTruth","15min",4);
        tables[2] = new Table(R.drawable.burger_king_icon, R.drawable.burger_king_entry, R.drawable.burger_king_detail, "BurgerKing", "#eat","15min",2);
        tables[3] = new Table(R.drawable.star_bucks_icon, R.drawable.starbucks_entry_image, R.drawable.starbucks_detail, "Need help with German", "#language training over #coffee","15min",5);
        tables[4] = new Table(R.drawable.custom_table_icon, R.drawable.p2, R.drawable.p2, "Deep thinking", "#DeepMind","15min",5);

    }

    public void confirmDialog() {
        DialogFragment newFragment = new DialogBoxFragment();
        newFragment.show(getFragmentManager(), "DialogBox");
    }

    public void searchDialog() {
        Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }
}
