package com.realestate.crm_backend.modules.crm.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.crm.domain.Lead;
import com.realestate.crm_backend.modules.crm.domain.LeadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService service;

    public LeadController(LeadService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeadDTO>> getAll() {
        return ResponseEntity.ok(
                service.findAll().stream().map(this::convertToDTO).toList()
        );
    }

    @PostMapping
    public ResponseEntity<LeadDTO> create(@Valid @RequestBody LeadDTO dto) {

        Lead lead = service.toEntity(dto);
        Lead saved = service.createLead(lead);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    private LeadDTO convertToDTO(Lead lead) {
        return new LeadDTO(
                lead.getId(),
                lead.getFirstName(),
                lead.getLastName(),
                lead.getEmail(),
                lead.getPhone(),
                lead.getAssignedAgentId(),
                lead.getStatus()
        );
    }

}
