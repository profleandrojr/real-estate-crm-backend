package com.realestate.crm_backend.modules.inventory.api;

import java.math.BigDecimal;

public record ListingDTO(Long id,
        String title,
        String description,
        BigDecimal price,
        String address,
        Integer bedrooms,
        Integer bathrooms,
        Double areaSquareMeters,
        Long listingAgentId,
        // Simple coordinates for the API
        Double latitude,
        Double longitude) {

}
