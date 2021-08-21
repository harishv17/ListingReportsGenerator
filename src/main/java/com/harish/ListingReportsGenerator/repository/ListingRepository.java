package com.harish.ListingReportsGenerator.repository;

import com.harish.ListingReportsGenerator.dto.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Integer> {
}
