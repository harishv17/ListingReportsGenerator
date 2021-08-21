package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.dto.reports.AverageListingSellingPriceReport;
import com.harish.ListingReportsGenerator.dto.reports.MakeDistributionReport;
import com.harish.ListingReportsGenerator.dto.reports.MostContactedListingAvgPriceReport;
import com.harish.ListingReportsGenerator.repository.ContactsRepository;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import com.harish.ListingReportsGenerator.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

@RestController
public class ReportsController {

    private final ReportService reportService;

    public ReportsController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/averageListingSellingPrices")
    public Set<AverageListingSellingPriceReport> getAverageListingSellingPrices() {
        return reportService.getAverageListingSellingPrices();
    }

    @GetMapping("/makeDistributionReport")
    public Set<MakeDistributionReport> getMakeDistributionReport() {
        return reportService.getMakeDistributionReport();
    }

    @GetMapping("/mostContactedListing")
    public MostContactedListingAvgPriceReport getMostContactedListingAvgPriceReport() {
        return reportService.getMostContactedListingAvgPriceReport();
    }
}
