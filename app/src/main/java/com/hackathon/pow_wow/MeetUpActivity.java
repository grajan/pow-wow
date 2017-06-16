package com.hackathon.pow_wow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MeetUpActivity extends AppCompatActivity {

    private GridView tablesGridView;

    private TablesAdapter tablesAdapter;

    private Table[] tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_up);

        fetchTablesArray();

        tablesGridView  = (GridView) findViewById(R.id.tablesGridView);
        tablesAdapter   = new TablesAdapter(this, tables);
        tablesGridView.setAdapter(tablesAdapter);
    }

    private void fetchTablesArray() {

        tables = new Table[10];

        for(int i = 0; i < 10; i++) {

            tables[i] = new Table();
        }
    }


}
