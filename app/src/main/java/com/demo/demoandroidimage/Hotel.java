package com.demo.demoandroidimage;

import java.io.Serializable;

public class Hotel implements Serializable {

    private String name;
    private String price;
    private String desc;
    private int imageResource;

    private int[] imageIds; // ชุด ID ของภาพสำหรับห้องนี้

    public Hotel(String name, String price, String desc , int imageResource,int[] imageIds) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.imageIds = imageIds;
        this.desc = desc;
    }

    public int[] getImageIds() {
        return imageIds;
    }
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public int getImageResource() {
        return imageResource;
    }
}