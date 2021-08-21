package com.harish.ListingReportsGenerator.repository;

import com.harish.ListingReportsGenerator.dto.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {
}
