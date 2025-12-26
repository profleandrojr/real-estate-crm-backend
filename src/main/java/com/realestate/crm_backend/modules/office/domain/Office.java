package com.realestate.crm_backend.modules.office.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "office_settings")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 5, scale = 2)
    private BigDecimal standardOfficeCut;

    // --- GETTERS & SETTERS --- 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getStandardOfficeCut() {
        return standardOfficeCut;
    }

    public void setStandardOfficeCut(BigDecimal standardOfficeCut) {
        this.standardOfficeCut = standardOfficeCut;
    }

}
