package com.realestate.crm_backend.modules.crm.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.crm.domain.Lead;
import com.realestate.crm_backend.modules.crm.domain.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LeadDTO> create(@RequestBody LeadDTO dto) {

        Lead lead = new Lead();
        lead.setName(dto.name());
        lead.setEmail(dto.email());
        lead.setPhone(dto.phone());
        lead.setSellingAgentId(dto.sellingAgentId());

        Lead saved = service.createLead(lead);
        return ResponseEntity.ok(new LeadDTO(saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone(),
                saved.getSellingAgentId()
        ));
    }

}
