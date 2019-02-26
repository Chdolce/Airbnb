package com.xykj.demo.Class;

import java.sql.Date;

public class Order_block {
    private String title;
    private Date start;

    public Order_block(String title, Date start, Date end, double cost, int show_house, int guestcount) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.cost = cost;
        this.show_house = show_house;
        this.guestcount = guestcount;
    }

    private Date end;
    private double cost;
    private int show_house;
    private int guestcount;

    public String getTitle() {
        return title;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public double getCost() {
        return cost;
    }

    public int getShow_house() {
        return show_house;
    }

    public int getGuestcount() {
        return guestcount;
    }
}
