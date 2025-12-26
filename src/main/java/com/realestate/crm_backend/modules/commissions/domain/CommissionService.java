package com.realestate.crm_backend.modules.commissions.domain;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.modules.commissions.repository.CommissionRepository;
import com.realestate.crm_backend.modules.crm.domain.Lead;
import com.realestate.crm_backend.modules.crm.domain.LeadService;
import com.realestate.crm_backend.modules.identity.domain.Agent;
import com.realestate.crm_backend.modules.identity.domain.AgentService;
import com.realestate.crm_backend.modules.inventory.domain.Listing;
import com.realestate.crm_backend.modules.inventory.domain.ListingService;

@Service
public class CommissionService {

    private final CommissionRepository repository;
    private final AgentService agentService;
    private final ListingService listingService;
    private final LeadService leadService;

    public CommissionService(CommissionRepository repository, AgentService agentService,
            ListingService listingService, LeadService leadService) {
        this.repository = repository;
        this.agentService = agentService;
        this.listingService = listingService;
        this.leadService = leadService;
    }

    public Commission processSale(Long listingId, Long leadId, BigDecimal salePrice) {
        Listing listing = listingService.findById(listingId);
        Lead lead = leadService.findById(leadId);

        Agent listingAgent = agentService.findById(listing.getListingAgentId());
        Agent sellingAgent = agentService.findById(lead.getSellingAgentId());

        // Basic Math: (Price * Rate) / 100
        BigDecimal listingPayout = salePrice.multiply(listingAgent.getBaseCommissionRate())
                .divide(new BigDecimal("100"));

        BigDecimal sellingPayout = salePrice.multiply(sellingAgent.getBaseCommissionRate())
                .divide(new BigDecimal("100"));

        Commission commission = new Commission();
        commission.setListingId(listingId);
        commission.setLeadId(leadId);
        commission.setTotalSalePrice(salePrice);
        commission.setListingAgentPayout(listingPayout);
        commission.setSellingAgentPayout(sellingPayout);
        commission.setTotalCommissionValue(listingPayout.add(sellingPayout));

        return repository.save(commission);
    }

}
