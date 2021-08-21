package com.harish.ListingReportsGenerator.transformer;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Component
public class CsvStringToClassTransformer {

    Logger logger = LoggerFactory.getLogger(CsvStringToClassTransformer.class);

    public <T> List<T> convert(String csvContent, Class<T> type) throws ListingsReportGeneratorException {
        try {
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
