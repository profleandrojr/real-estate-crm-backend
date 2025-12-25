package com.realestate.crm_backend.modules.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.crm_backend.modules.identity.domain.Realtor;

public interface RealtorRepository extends JpaRepository<Realtor, Long> {

}
