package com.harish.ListingReportsGenerator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class ListingEntity {
    @Id
    private int id;
    private String make;
    private long price;
    private long mileage;
    private String sellarType;

    public ListingEntity(int id, String make, long price, long mileage, String sellarType) {
        this.id = id;
        this.make = make;
        this.price = price;
        this.mileage = mileage;
        this.sellarType = sellarType;
    }

    public ListingEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getSellarType() {
        return sellarType;
    }

    public void setSellarType(String sellarType) {
        this.sellarType = sellarType;
    }
}