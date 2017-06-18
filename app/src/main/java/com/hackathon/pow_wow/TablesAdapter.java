package com.hackathon.pow_wow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

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

        final TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(currentTable.getTableDiscussionTopic());

        final TextView tagView = (TextView) convertView.findViewById(R.id.topic);
        tagView.setText(currentTable.getTag());

        final TextView numberOfPeople = (TextView) convertView.findViewById(R.id.no_of_peoples);
        Random ran = new Random();
        numberOfPeople.setText(String.valueOf(ran.nextInt(15)+1));


        final ImageView imageView = (ImageView)convertView.findViewById(R.id.icon);
        imageView.setImageResource(currentTable.getTableIconImageResource());

        final ImageView entryImageView = (ImageView)convertView.findViewById(R.id.entryImage);
        entryImageView.setImageResource(currentTable.getTableEntryImageResource());


        return convertView;
    }


}
