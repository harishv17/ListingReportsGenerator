package com.harish.ListingReportsGenerator.service;

import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import com.harish.ListingReportsGenerator.transformer.CsvStringToClassTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ListingsReportsGeneratorService {

    Logger logger = LoggerFactory.getLogger(CsvStringToClassTransformer.class);
    private CsvStringToClassTransformer csvStringToClassTransformer;
    public ListingsReportsGeneratorService(CsvStringToClassTransformer csvStringToClassTransformer) {
        this.csvStringToClassTransformer = csvStringToClassTransformer;
    }

    public <T> List<T> saveFileData(MultipartFile listingFile, Class<T> type) throws ListingsReportGeneratorException {
        try {
            String content = new String(listingFile.getBytes(), StandardCharsets.UTF_8);
            List<T> listings = csvStringToClassTransformer.convert(content, type);
            System.out.println(listings);
            return listings;
        } catch (IOException e) {
            logger.error("Error translating CSV file", e);
            throw new ListingsReportGeneratorException("Error translating CSV file", e);
        }
    }

}
