package com.realestate.crm_backend.dto;

import java.math.BigDecimal;

public record ListingDTO(Long id,
        String title,
        String description,
        BigDecimal price,
        String address,
        Integer bedrooms,
        Integer bathrooms,
        Double areaSquareMeters,
        // Simple coordinates for the API
        Double latitude,
        Double longitude) {

}
