package com.harish.ListingReportsGenerator.repository;

import com.harish.ListingReportsGenerator.dto.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public class ListingRepository implements JpaRepository<Listing, Integer> {
}
