package com.realestate.crm_backend.modules.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.crm_backend.modules.crm.domain.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
