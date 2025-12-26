package com.realestate.crm_backend.modules.commissions.api;

import java.math.BigDecimal;

public record AgentDashboardDTO(
        String agentName,
        Long totalDeals,
        BigDecimal totalEarnings
        ) {

}
