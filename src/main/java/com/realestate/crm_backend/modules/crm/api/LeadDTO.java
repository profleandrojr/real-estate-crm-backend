package com.realestate.crm_backend.modules.crm.api;

import com.realestate.crm_backend.modules.crm.domain.LeadStatus;

public record LeadDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        Long assignedAgentId,
        LeadStatus status
        ) {

}
