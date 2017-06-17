package com.hackathon.pow_wow;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class TableActivity extends FragmentActivity implements SwipeFragmentInterface {

    private Table table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        table = (Table) getIntent().getSerializableExtra("Table");
    }

    @Override
    public int[] getImageArray() {

        int[] tableImageResources = null;
        //tableImageResources = table.getTableImageResource();
        Random rn = new Random();
        int[] data = new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
        return new int[] { data[rn.nextInt(5)]};

//        return new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
    }
}
