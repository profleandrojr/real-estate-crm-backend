package com.realestate.crm_backend.modules.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.crm_backend.modules.identity.domain.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {

}
