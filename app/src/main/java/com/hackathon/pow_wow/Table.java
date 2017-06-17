package com.hackathon.pow_wow;

import java.io.Serializable;

/**
 * Created by Rajan on 6/16/17.
 */

public class Table implements Serializable {

    private int[] tableImageResource;

    private String timeValue;

    private Integer numberOfPeople;

    private int[] peopleImageResource;

    private TableType type;

    private String tag;

    static int count = 0;


    public Table() {

        this.tableImageResource = new int[] { R.drawable.table_white, R.drawable.c2, R.drawable.table_, R.drawable.custom };

        Table.count++;

        int time = (int) (Math.random()*100)%45 + 1;

        this.timeValue = time + "min more";
        this.numberOfPeople = 10;
        this.peopleImageResource = new int[]{ R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};

        this.type = TableType.TableTypeCoupon;
    }

    public Table(int[] tableImageResource, String timeValue, Integer numberOfPeople, TableType type) {

        this.tableImageResource = tableImageResource;
        this.timeValue = timeValue;
        this.numberOfPeople = numberOfPeople;
        this.type = type;
    }

    public int[] getTableImageResource() {
        return tableImageResource;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public String getTag() {
        return timeValue;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public TableType getType() {
        return type;
    }

    public int[] getPeopleImageResource() {
        return peopleImageResource;
    }
}
