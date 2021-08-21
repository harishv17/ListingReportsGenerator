package com.harish.ListingReportsGenerator.dto.reports;

public class MostContactedListingAvgPriceReport {
    private String averagePrice;

    public MostContactedListingAvgPriceReport(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    public MostContactedListingAvgPriceReport() {
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }
}
