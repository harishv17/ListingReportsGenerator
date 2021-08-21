package com.harish.ListingReportsGenerator.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @NotNull
    private int id;
    @Size(min = 1)
    private String make;
    @NotNull
    private long price;
    @NotNull
    private long mileage;
    @Size(min = 1)
    private String sellerType;

    public Listing(int id, String make, long price, long mileage, String sellerType) {
        this.id = id;
        this.make = make;
        this.price = price;
        this.mileage = mileage;
        this.sellerType = sellerType;
    }

    public Listing() {
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

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", sellerType='" + sellerType + '\'' +
                '}';
    }
}
