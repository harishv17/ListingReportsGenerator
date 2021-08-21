package com.harish.ListingReportsGenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ListingsReportsGeneratorController {

    @PostMapping("/listings")
    public String uploadListingFile(@RequestParam("listings") MultipartFile file) {
        return null;
    }
}
