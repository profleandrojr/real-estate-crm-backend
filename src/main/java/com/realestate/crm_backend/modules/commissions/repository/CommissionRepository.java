package com.realestate.crm_backend.modules.commissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.crm_backend.modules.commissions.domain.Commission;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

}
