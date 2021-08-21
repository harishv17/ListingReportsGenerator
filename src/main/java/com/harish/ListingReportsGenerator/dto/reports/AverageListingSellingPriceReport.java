package com.harish.ListingReportsGenerator.dto.reports;

import java.util.Objects;

public class AverageListingSellingPriceReport {

    private String sellerType;
    private String average;

    public AverageListingSellingPriceReport(String sellerType, String average) {
        this.sellerType = sellerType;
        this.average = average;
    }

    public AverageListingSellingPriceReport() {
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AverageListingSellingPriceReport that = (AverageListingSellingPriceReport) o;
        return sellerType.equals(that.sellerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerType);
    }
}
