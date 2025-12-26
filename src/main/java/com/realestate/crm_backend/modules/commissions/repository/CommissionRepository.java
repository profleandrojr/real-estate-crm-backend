package com.realestate.crm_backend.modules.commissions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.realestate.crm_backend.modules.commissions.domain.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

    @Query("SELECT c FROM Commission c "
            + "JOIN Listing l ON c.listingId = l.id "
            + "JOIN Lead ld ON c.leadId = ld.id "
            + "WHERE l.listingAgentId = :agentId OR ld.sellingAgentId = :agentId")

    List<Commission> findAllByAgentId(@Param("agentId") Long agentId);

}
