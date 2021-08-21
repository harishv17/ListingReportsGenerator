package com.harish.ListingReportsGenerator.controller;

import com.harish.ListingReportsGenerator.dto.Contacts;
import com.harish.ListingReportsGenerator.dto.Listing;
import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import com.harish.ListingReportsGenerator.repository.ContactsRepository;
import com.harish.ListingReportsGenerator.repository.ListingRepository;
import com.harish.ListingReportsGenerator.transformer.CsvFileTransformer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ContactsController {

    private final CsvFileTransformer csvFileTransformer;
    private final ContactsRepository contactsRepository;
    public ContactsController(CsvFileTransformer csvFileTransformer, ContactsRepository contactsRepository) {
        this.csvFileTransformer = csvFileTransformer;
        this.contactsRepository = contactsRepository;
    }
    @PostMapping("/contacts")
    public List<Contacts> uploadListingFile(@RequestParam("contacts") MultipartFile file) throws ListingsReportGeneratorException {
        List<Contacts> contacts = csvFileTransformer.convert(file, Contacts.class);
        contactsRepository.saveAll(contacts);
        return contacts;
    }
}
