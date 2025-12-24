package com.realestate.crm_backend.modules.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.crm_backend.modules.inventory.domain.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    // Geospatial queries go here later (e.g., find properties within 5km)
}
