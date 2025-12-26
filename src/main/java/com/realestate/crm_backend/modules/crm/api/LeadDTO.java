package com.realestate.crm_backend.modules.crm.api;

public record LeadDTO(
        Long id,
        String name,
        String email,
        String phone,
        Long sellingAgentId
        ) {

}
