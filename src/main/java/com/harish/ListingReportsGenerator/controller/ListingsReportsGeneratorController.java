package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.service.ListingsReportsGeneratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ListingsReportsGeneratorController {

    private ListingsReportsGeneratorService listingsReportsGeneratorService;
    public ListingsReportsGeneratorController(ListingsReportsGeneratorService listingsReportsGeneratorService) {
        this.listingsReportsGeneratorService = listingsReportsGeneratorService;
    }
    @PostMapping("/listings")
    public List<Listing> uploadListingFile(@RequestParam("listings") MultipartFile file) throws ListingsReportGeneratorException {
        return listingsReportsGeneratorService.saveFileData(file, Listing.class);
    }
}
