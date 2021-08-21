package com.harish.ListingReportsGenerator.service;

import com.harish.ListingReportsGenerator.ListingsReportGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {
    Logger logger = LoggerFactory.getLogger(StorageService.class);
    private static final String TEMP_FILE_LOCATION = "/temp";
    public Path saveFile(MultipartFile multipartFile, String fileName) throws ListingsReportGeneratorException {
        try {
            Files.createDirectory(Paths.get(TEMP_FILE_LOCATION));
            Path filePath = Paths.get(TEMP_FILE_LOCATION, fileName);
            Files.deleteIfExists(Paths.get(TEMP_FILE_LOCATION, fileName));
            Files.createFile(filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Error creating temp directory", e);
            throw new ListingsReportGeneratorException("Error creating temp directory", e);
        }
    }
}
