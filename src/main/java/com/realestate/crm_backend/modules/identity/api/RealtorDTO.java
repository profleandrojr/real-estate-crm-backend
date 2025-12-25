package com.realestate.crm_backend.modules.identity.api;

import java.math.BigDecimal;

public record RealtorDTO(
        Long id,
        String name,
        String licenseNumber,
        BigDecimal baseCommissionRate
        ) {

}
