package com.realestate.crm_backend.modules.commissions.api;

import java.math.BigDecimal;

public record CommissionRequestDTO(
        Long listingId,
        Long leadId,
        BigDecimal salePrice
        ) {

}
