package com.realestate.crm_backend.modules.identity.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.modules.identity.repository.RealtorRepository;

import jakarta.transaction.Transactional;

@Service
public class RealtorService {

    private final RealtorRepository repository;

    public RealtorService(RealtorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Realtor save(Realtor realtor) {
        // Business Rule: Ensure license numbers are always uppercase
        if (realtor.getLicenseNumber() != null) {
            realtor.setLicenseNumber(realtor.getLicenseNumber().toUpperCase());
        }
        return repository.save(realtor);
    }

    public List<Realtor> findAll() {
        return repository.findAll();
    }

    public Optional<Realtor> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Validates if a Realtor exists. This is what other modules (Inventory/CRM)
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
