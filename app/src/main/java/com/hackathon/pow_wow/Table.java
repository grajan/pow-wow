package com.hackathon.pow_wow;

/**
 * Created by Rajan on 6/16/17.
 */

public class Table {

    private int tableImageResource;

    private String timeValue;

    private Integer numberOfPeople;

    private TableType type;

    private String tag;

    public Table() {

        this.tableImageResource = R.drawable.table;
        this.timeValue = "45min";
        this.numberOfPeople = 10;
        this.type = TableType.TableTypeCoupon;
    }

    public Table(String tableImage, String timeValue, Integer numberOfPeople, TableType type) {

        this.tableImageResource = R.drawable.table;
        this.timeValue = timeValue;
        this.numberOfPeople = numberOfPeople;
        this.type = type;
    }

    public int getTableImageResource() {
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
}
