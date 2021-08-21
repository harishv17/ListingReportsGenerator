package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.dto.reports.AverageListingSellingPriceReport;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.dto.reports.MakeDistributionReport;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class ReportsController {

    private final ListingRepository listingRepository;

    public ReportsController(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @GetMapping("/averageListingSellingPrices")
    public Set<AverageListingSellingPriceReport> getAverageListingSellingPrices() {
        var listingSellingPriceReports = new HashSet<AverageListingSellingPriceReport>();
        List<Listing> listings = listingRepository.findAll();
        Map<String, Long> sellerTypeToAvgPriceMap = Optional.of(listings).orElse(new ArrayList<>()).stream()
                .collect(Collectors.toMap(Listing::getSellerType, Listing::getPrice, (price1,price2) -> (price1+price2)/2));

        sellerTypeToAvgPriceMap.forEach((sellerType, average) -> listingSellingPriceReports.add(new AverageListingSellingPriceReport(sellerType, formatCurrency(average))));
        return listingSellingPriceReports;
    }

    @GetMapping("/makeDistributionReport")
    public Set<MakeDistributionReport> getMakeDistributionReport() {
        var makeDistributionReports = new HashSet<MakeDistributionReport>();
        List<Listing> listings = listingRepository.findAll();
        long totalCount = Optional.ofNullable(listings).map(List::size).orElse(0);

        Map<String, Long> makeToCountMap = Optional.of(listings).orElse(new ArrayList<>()).stream()
                .collect(Collectors.groupingBy(Listing::getMake, Collectors.counting()));

        makeToCountMap.forEach((make, count) ->makeDistributionReports.add(new MakeDistributionReport(make, count, totalCount)));
        return makeDistributionReports;
    }

    private String formatCurrency(long value) {
        NumberFormat decimalFormat = DecimalFormat.getCurrencyInstance(Locale.GERMANY);
        return String.format("%s %d,-", DecimalFormatSymbols.getInstance(Locale.GERMANY).getCurrencySymbol(), value);
    }
}
