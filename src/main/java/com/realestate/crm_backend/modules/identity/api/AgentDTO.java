package com.realestate.crm_backend.modules.identity.api;

import java.math.BigDecimal;

public record AgentDTO(
        Long id,
        String name,
        String licenseNumber,
        BigDecimal baseCommissionRate,
        Boolean isRealtor
        ) {

}
