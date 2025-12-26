package com.realestate.crm_backend.modules.crm.domain;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.config.ResourceNotFoundException;
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

    public Lead createLead(Lead lead) {

        if (lead.getSellingAgentId() != null && !agent.exists(lead.getSellingAgentId())) {
            throw new ResourceNotFoundException("Selling agent not found with ID: " + lead.getSellingAgentId());
        }

        return repository.save(lead);
    }

    public Lead findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found with ID" + id));
    }

}
