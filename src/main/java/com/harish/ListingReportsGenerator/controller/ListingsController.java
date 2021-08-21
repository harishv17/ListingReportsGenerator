package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import com.harish.ListingReportsGenerator.transformer.CsvFileTransformer;
import com.harish.ListingReportsGenerator.validator.BeanValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ListingsController {

    private final CsvFileTransformer csvFileTransformer;
    private final ListingRepository listingRepository;
    private final BeanValidator beanValidator;
    public ListingsController(CsvFileTransformer csvFileTransformer, ListingRepository listingRepository, BeanValidator beanValidator) {
        this.csvFileTransformer = csvFileTransformer;
        this.listingRepository = listingRepository;
        this.beanValidator = beanValidator;
    }
    @PostMapping("/listings")
    public List<Listing> uploadListingFile(@RequestParam("listings") MultipartFile file) throws ListingsReportGeneratorException {
        List<Listing> listings = csvFileTransformer.convert(file, Listing.class);
        System.out.println("-------------------------------");
        System.out.println(listings);
        listingRepository.saveAll(listings);
        return listings;
    }
}
