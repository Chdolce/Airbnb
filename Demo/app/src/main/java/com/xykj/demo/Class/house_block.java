package com.xykj.demo.Class;

import java.io.Serializable;

public class house_block implements Serializable {
    private String house_picture;//图片
    private String house_title;//房子标题
    private int price;//房子费用
    private String house_name;//房子详细名称
    private int house_id;//房子ID
    private String house_type;//房子类型 1，2, 3， 4  （公寓,普通民宅，附属单元， 特色房源）
    private String house_country;
    private String house_province;
    private String house_city;
    private String house_address;  //地址
    private double house_longitude;//经度
    private double house_latitude;//纬度
    private int house_capacity;//可住容量

    public house_block() {
    }

    public house_block(String show_house, String house_title, int price, String house_name) {
        this.house_picture = show_house;
        this.house_title = house_title;
        this.price = price;
        this.house_name = house_name;
    }

    public house_block(String show_house, String house_title, int price, String house_name, int house_id) {
        this.house_picture = show_house;
        this.house_title = house_title;
        this.price = price;
        this.house_name = house_name;
        this.house_id = house_id;
    }

    public String getHouse_type() {
        return house_type;
    }

    public void setHouse_type(String house_type) {
        this.house_type = house_type;
    }

    public String getHouse_country() {
        return house_country;
    }

    public void setHouse_country(String house_country) {
        this.house_country = house_country;
    }

    public String getHouse_province() {
        return house_province;
    }

    public void setHouse_province(String house_province) {
        this.house_province = house_province;
    }

    public String getHouse_city() {
        return house_city;
    }

    public void setHouse_city(String house_city) {
        this.house_city = house_city;
    }

    public String getHouse_address() {
        return house_address;
    }

    public void setHouse_address(String house_address) {
        this.house_address = house_address;
    }

    public double getHouse_longitude() {
        return house_longitude;
    }

    public void setHouse_longitude(double house_longitude) {
        this.house_longitude = house_longitude;
    }

    public double getHouse_latitude() {
        return house_latitude;
    }

    public void setHouse_latitude(double house_latitude) {
        this.house_latitude = house_latitude;
    }

    public int getHouse_capacity() {
        return house_capacity;
    }

    public void setHouse_capacity(int house_capacity) {
        this.house_capacity = house_capacity;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

    public String getHouse_picture() {
        return house_picture;
    }

    public void setHouse_picture(String house_picture) {
        this.house_picture = house_picture;
    }

    public String getHouse_title() {
        return house_title;
    }

    public void setHouse_title(String house_title) {
        this.house_title = house_title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




}
