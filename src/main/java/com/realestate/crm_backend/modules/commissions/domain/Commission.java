package com.realestate.crm_backend.modules.commissions.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "commissions")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long listingId;

    @Column(nullable = false)
    private Long leadId;

    private BigDecimal totalSalePrice;
    private BigDecimal totalCommissionValue;

    private BigDecimal listingAgentPayout;
    private BigDecimal sellingAgentPayout;
    private BigDecimal officeCut;

    private LocalDateTime processedAt;

    @PrePersist
    protected void onCreate() {
        processedAt = LocalDateTime.now();
    }

    // --- GETTERS & SETTERS --
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public BigDecimal getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(BigDecimal totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public BigDecimal getTotalCommissionValue() {
        return totalCommissionValue;
    }

    public void setTotalCommissionValue(BigDecimal totalCommissionValue) {
        this.totalCommissionValue = totalCommissionValue;
    }

    public BigDecimal getListingAgentPayout() {
        return listingAgentPayout;
    }

    public void setListingAgentPayout(BigDecimal listingAgentPayout) {
        this.listingAgentPayout = listingAgentPayout;
    }

    public BigDecimal getSellingAgentPayout() {
        return sellingAgentPayout;
    }

    public void setSellingAgentPayout(BigDecimal sellingAgentPayout) {
        this.sellingAgentPayout = sellingAgentPayout;
    }

    public BigDecimal getOfficeCut() {
        return officeCut;
    }

    public void setOfficeCut(BigDecimal officeCut) {
        this.officeCut = officeCut;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

}
