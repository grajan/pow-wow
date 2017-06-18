package com.hackathon.pow_wow;

import java.io.Serializable;

/**
 * Created by Rajan on 6/16/17.
 */

public class Table implements Serializable {

    private int tableIconImageResource;

    private int tableEntryImageResource;

    private int tableDetailImageResource;

    private String tableDiscussionTopic;

    private String tag;

    private String timeValue;

    private Integer numberOfPeople;

//    private int[] peopleImageResource;
//
//    private TableType type;
//
//    private String tag;
//
//    static int count = 0;
//
//
//    public Table() {
//
//        this.tableImageResource = R.drawable.table_white;
//
//        Table.count++;
//
//        int time = (int) (Math.random()*100)%45 + 1;
//
//        this.timeValue = time + "min more";
//        this.numberOfPeople = 10;
//        this.peopleImageResource = new int[]{ R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
//
//        this.type = TableType.TableTypeCoupon;
//    }


    public Table(int tableIconImageResource, int tableEntryImageResource, int tableDetailImageResource, String tableDiscussionTopic, String tag, String timeValue, Integer numberOfPeople) {
        this.tableIconImageResource = tableIconImageResource;
        this.tableEntryImageResource = tableEntryImageResource;
        this.tableDetailImageResource = tableDetailImageResource;
        this.tableDiscussionTopic = tableDiscussionTopic;
        this.tag = tag;
        this.timeValue = timeValue;
        this.numberOfPeople = numberOfPeople;
    }

    public int getTableIconImageResource() {
        return tableIconImageResource;
    }

    public int getTableEntryImageResource() {
        return tableEntryImageResource;
    }

    public int getTableDetailImageResource() {
        return tableDetailImageResource;
    }

    public String getTableDiscussionTopic() {
        return tableDiscussionTopic;
    }

    public String getTag() {
        return tag;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }
}
