package com.realestate.crm_backend.modules.identity.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.config.ResourceNotFoundException;
import com.realestate.crm_backend.modules.identity.repository.AgentRepository;

import jakarta.transaction.Transactional;

@Service
public class AgentService {

    private final AgentRepository repository;

    public AgentService(AgentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Agent save(Agent agent) {
        // Business Rule: Ensure license numbers are always uppercase
        if (agent.getLicenseNumber() != null) {
            agent.setLicenseNumber(agent.getLicenseNumber().toUpperCase());
        }
        return repository.save(agent);
    }

    public Agent findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found with ID: " + id));
    }

    public List<Agent> findAll() {
        return repository.findAll();
    }

    public Agent findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent not found with ID" + id));
    }

    /**
     * Validates if a Agent exists. This is what other modules (Inventory/CRM)
     * will call.
     */
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
