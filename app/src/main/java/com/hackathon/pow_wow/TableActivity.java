package com.hackathon.pow_wow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class TableActivity extends Activity{

    private Table table;

    private Button btnJoin;

    private Button btnChat;
    private TextView location;

    private ImageView btnMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);


//        table = (Table) getIntent().getSerializableExtra("Table");
//
        btnJoin = (Button) findViewById(R.id.btn_join);
        location = (TextView) findViewById(R.id.lbl_mapbtn);
        btnChat = (Button) findViewById(R.id.btn_chat);
        btnMap = (ImageView) findViewById(R.id.btn_map);

        btnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (btnChat.getVisibility() == View.INVISIBLE) {

                    btnChat.setVisibility(View.VISIBLE);
                    btnMap.setVisibility(View.VISIBLE);
                    location.setVisibility(View.VISIBLE);
                    btnJoin.setText("Unjoin");
                }
                else {

                    btnChat.setVisibility(View.INVISIBLE);
                    btnMap.setVisibility(View.INVISIBLE);
                    location.setVisibility(View.INVISIBLE);
                    btnJoin.setText("Join");
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableActivity.this,MapActivity.class));
            }
        });
    }

//    @Override
//    public int[] getImageArray() {
//
//        int[] tableImageResources = null;
//        //tableImageResources = table.getTableImageResource();
//        Random rn = new Random();
//        int[] data = new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
//        return new int[] { R.drawable.c1, R.drawable.c2, R.drawable.c3};
//
////        return new int[]{ R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c1, R.drawable.c2};
//    }
}
