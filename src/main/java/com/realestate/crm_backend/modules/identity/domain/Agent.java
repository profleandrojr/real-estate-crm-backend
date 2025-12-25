package com.realestate.crm_backend.modules.identity.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal baseCommissionRate;

    @Column(nullable = false)
    private boolean isRealtor = false;

    // --- GETTERS & SETTERS ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public BigDecimal getBaseCommissionRate() {
        return baseCommissionRate;
    }

    public void setBaseCommissionRate(BigDecimal baseCommissionRate) {
        this.baseCommissionRate = baseCommissionRate;
    }

    public boolean isRealtor() {
        return isRealtor;
    }

    public void setRealtor(boolean isRealtor) {
        this.isRealtor = isRealtor;
    }

}
