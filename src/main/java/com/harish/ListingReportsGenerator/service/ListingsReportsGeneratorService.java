package com.harish.ListingReportsGenerator.service;

import com.harish.ListingReportsGenerator.ListingsReportGeneratorException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public class ListingsReportsGeneratorService {

    private StorageService storageService;
    public ListingsReportsGeneratorService(StorageService storageService) {
        this.storageService = storageService;
    }

    public <T> void saveFileData(MultipartFile listingFile, T type) throws ListingsReportGeneratorException {
        Path filePath = storageService.saveFile(listingFile, type.toString()+".csv");
    }

}
