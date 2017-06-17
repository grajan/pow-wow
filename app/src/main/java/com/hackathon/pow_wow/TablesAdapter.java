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
//        final ImageView user1ImageView = (ImageView)convertView.findViewById(R.id.user1);
//        final ImageView user2ImageView = (ImageView)convertView.findViewById(R.id.user2);
//        final ImageView user3ImageView = (ImageView)convertView.findViewById(R.id.user3);
//        final ImageView user4ImageView = (ImageView)convertView.findViewById(R.id.user4);
//        final ImageView user5ImageView = (ImageView)convertView.findViewById(R.id.user5);
//        final TextView tagsTextView = (TextView)convertView.findViewById(R.id.tags);
        final TextView timeTextView = (TextView)convertView.findViewById(R.id.time_to_go);

        imageView.setImageResource(currentTable.getTableImageResource()[0]);

        int[] peopleImage = currentTable.getPeopleImageResource();
//        user1ImageView.setImageResource(peopleImage[0]);
//        user2ImageView.setImageResource(peopleImage[1]);
//        user3ImageView.setImageResource(peopleImage[2]);
//        user4ImageView.setImageResource(peopleImage[3]);
//        user5ImageView.setImageResource(peopleImage[4]);

//        tagsTextView.setText(currentTable.getTag());
        timeTextView.setText(currentTable.getTimeValue());

        return convertView;
    }


}
