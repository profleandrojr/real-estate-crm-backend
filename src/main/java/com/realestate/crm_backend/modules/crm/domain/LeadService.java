package com.realestate.crm_backend.modules.crm.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.config.ResourceNotFoundException;
import com.realestate.crm_backend.modules.crm.api.LeadDTO;
import com.realestate.crm_backend.modules.crm.repository.LeadRepository;
import com.realestate.crm_backend.modules.identity.domain.AgentService;

@Service
public class LeadService {

    private final LeadRepository repository;
    private final AgentService agent;

    public LeadService(LeadRepository repository, AgentService agent) {
        this.agent = agent;
        this.repository = repository;
    }

    public List<Lead> findAll() {
        return repository.findAll();
    }

    public Lead createLead(Lead lead) {

        if (lead.getAssignedAgentId() != null && !agent.exists(lead.getAssignedAgentId())) {
            throw new ResourceNotFoundException("Selling agent not found with ID: "
                    + lead.getAssignedAgentId());
        }

        return repository.save(lead);
    }

    public Lead findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with ID" + id));
    }

    public Lead toEntity(LeadDTO dto) {

        Lead lead = new Lead();

        lead.setFirstName(dto.firstName());
        lead.setLastName(dto.lastName());
        lead.setEmail(dto.email());
        lead.setPhone(dto.phone());
        lead.setStatus(dto.status() != null ? dto.status() : LeadStatus.NEW);
        lead.setAssignedAgentId(dto.assignedAgentId());

        return lead;
    }

}
