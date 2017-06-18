package com.hackathon.pow_wow;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class TableActivity extends FragmentActivity implements SwipeFragmentInterface {

    private Table table;

    private Button btnJoin;

    private Button btnChat;

    private Button btnMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        table = (Table) getIntent().getSerializableExtra("Table");

        btnJoin = (Button) findViewById(R.id.btn_join);
        btnChat = (Button) findViewById(R.id.btn_chat);
        btnMap = (Button) findViewById(R.id.btn_map);

        btnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (btnChat.getVisibility() == View.GONE) {

                    btnChat.setVisibility(View.VISIBLE);
                    btnMap.setVisibility(View.VISIBLE);
                    btnJoin.setText("Unjoin");
                }
                else {

                    btnChat.setVisibility(View.GONE);
                    btnMap.setVisibility(View.GONE);
                    btnJoin.setText("Join");
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(this, );
            }
        });
    }

    @Override
    public int[] getImageArray() {

        int[] tableImageResources = null;
        //tableImageResources = table.getTableImageResource();
        Random rn = new Random();
        int[] data = new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
        return new int[] { R.drawable.c1, R.drawable.c2, R.drawable.c3};

//        return new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
    }
}
