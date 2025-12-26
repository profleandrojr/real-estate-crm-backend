package com.realestate.crm_backend.modules.office.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    // For now, return a default, but later this will come from the DB
    public BigDecimal getOfficeCutPercentage() {
        return new BigDecimal("20.00");
    }
}
