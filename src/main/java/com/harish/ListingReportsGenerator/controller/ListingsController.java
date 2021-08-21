package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.dto.Contacts;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import com.harish.ListingReportsGenerator.transformer.CsvFileTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ListingsController {

    private final CsvFileTransformer csvFileTransformer;
    private final ListingRepository listingRepository;

    public ListingsController(CsvFileTransformer csvFileTransformer, ListingRepository listingRepository) {
        this.csvFileTransformer = csvFileTransformer;
        this.listingRepository = listingRepository;
    }

    @PostMapping("/listings")
    public List<Listing> uploadListingFile(@RequestParam("listings") MultipartFile file) throws ListingsReportGeneratorException {
        List<Listing> listings = csvFileTransformer.convert(file, Listing.class);
        System.out.println("-------------------------------");
        System.out.println(listings);
        listingRepository.saveAll(listings);
        return listings;
    }

    @GetMapping("/listings")
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }
}
