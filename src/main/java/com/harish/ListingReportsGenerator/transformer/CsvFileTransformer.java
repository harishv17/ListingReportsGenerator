package com.harish.ListingReportsGenerator.transformer;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvFileTransformer {

    Logger logger = LoggerFactory.getLogger(CsvFileTransformer.class);

    public <T> List<T> convert(MultipartFile listingFile, Class<T> type) throws ListingsReportGeneratorException {
        try {
            String csvContent = new String(listingFile.getBytes(), StandardCharsets.UTF_8);
            var contentReader = new StringReader(csvContent);
            var csvMapper = new CsvMapper();
            CsvSchema csvSchema = new CsvMapper().schemaFor(type).withSkipFirstDataRow(true).withColumnSeparator(',');
            MappingIterator<T> typeMappingIterator = csvMapper.readerFor(type).with(csvSchema).readValues(contentReader);
            return typeMappingIterator.readAll();
        } catch (IOException e) {
            logger.error("Error translating CSV file", e);
            throw new ListingsReportGeneratorException("Error translating CSV file", e);
        }
    }
}
