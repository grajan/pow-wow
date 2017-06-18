package com.hackathon.pow_wow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Rajan on 6/16/17.
 */

public class TablesAdapter extends BaseAdapter {

    public final Context mContext;

    public final Table[] tables;

    public TablesAdapter(Context context, Table[] tables) {
        this.mContext = context;
        this.tables = tables;
    }

    @Override
    public int getCount() {
        return tables.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Table currentTable = tables[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.table_grid_cell, null);
        }

        final ImageView imageView = (ImageView)convertView.findViewById(R.id.table_image);
//        final TextView timeTextView = (TextView)convertView.findViewById(R.id.time_to_go);

        imageView.setImageResource(R.drawable.ads_1);

//        int[] peopleImage = currentTable.getPeopleImageResource();

//        timeTextView.setText(currentTable.getTimeValue());

        return convertView;
    }


}
