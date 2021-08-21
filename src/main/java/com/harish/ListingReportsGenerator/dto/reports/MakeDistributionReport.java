package com.harish.ListingReportsGenerator.dto.reports;

import java.util.Objects;

public class MakeDistributionReport implements Comparable<MakeDistributionReport> {
    private String make;
    private long distribution;

    public MakeDistributionReport(String make, long makeCount, long totalCount) {
        this.make = make;
        this.distribution = (makeCount * 100) / totalCount;
    }

    public MakeDistributionReport() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public long getDistribution() {
        return distribution;
    }

    public void setDistribution(long distribution) {
        this.distribution = distribution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MakeDistributionReport that = (MakeDistributionReport) o;
        return Objects.equals(make, that.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make);
    }

    @Override
    public int compareTo(MakeDistributionReport o) {
        return Long.compare(this.distribution, o.distribution);
    }
}
