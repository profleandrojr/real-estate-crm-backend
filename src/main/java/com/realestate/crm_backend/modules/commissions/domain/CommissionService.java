package com.realestate.crm_backend.modules.commissions.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.realestate.crm_backend.modules.commissions.api.AgentDashboardDTO;
import com.realestate.crm_backend.modules.commissions.repository.CommissionRepository;
import com.realestate.crm_backend.modules.crm.domain.Lead;
import com.realestate.crm_backend.modules.crm.domain.LeadService;
import com.realestate.crm_backend.modules.identity.domain.Agent;
import com.realestate.crm_backend.modules.identity.domain.AgentService;
import com.realestate.crm_backend.modules.inventory.domain.Listing;
import com.realestate.crm_backend.modules.inventory.domain.ListingService;
import com.realestate.crm_backend.modules.office.domain.OfficeService;

@Service
public class CommissionService {

    private final CommissionRepository repository;
    private final AgentService agentService;
    private final ListingService listingService;
    private final LeadService leadService;
    private final OfficeService officeService;

    public CommissionService(CommissionRepository repository, AgentService agentService,
            ListingService listingService, LeadService leadService,
            OfficeService officeService) {
        this.repository = repository;
        this.agentService = agentService;
        this.listingService = listingService;
        this.leadService = leadService;
        this.officeService = officeService;
    }

    public AgentDashboardDTO getAgentPerformance(Long agentId) {

        // Reusing our findById from Identity
        Agent agent = agentService.findById(agentId);

        List<Commission> commissions = repository.findAllByAgentId(agentId);

        // Filter and sum logic
        BigDecimal totalEarnings = commissions.stream()
                .map(c -> {

                    // Check if the agent was the listing agent for this specific commission
                    Listing listing = listingService.findById(c.getListingId());
                    if (listing.getListingAgentId().equals(agentId)) {
                        return c.getListingAgentPayout();
                    } else {
                        return c.getSellingAgentPayout();
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new AgentDashboardDTO(
                agent.getFullName(),
                (long) commissions.size(),
                totalEarnings
        );
    }

    public Commission processSale(Long listingId, Long leadId, BigDecimal salePrice) {
        Listing listing = listingService.findById(listingId);
        Lead lead = leadService.findById(leadId);

        Agent listingAgent = agentService.findById(listing.getListingAgentId());
        Agent sellingAgent = agentService.findById(lead.getAssignedAgentId());

        // Get the rule from the Office
        BigDecimal officeRate = officeService.getOfficeCutPercentage()
                .divide(new BigDecimal("100"));

        // Gross Commissions
        BigDecimal listingGross = salePrice.multiply(listingAgent.getBaseCommissionRate())
                .divide(new BigDecimal("100"));
        BigDecimal sellingGross = salePrice.multiply(sellingAgent.getBaseCommissionRate())
                .divide(new BigDecimal("100"));

        BigDecimal totalGross = listingGross.add(sellingGross);

        // Delegate the "Cut"
        BigDecimal officeCutValue = totalGross.multiply(officeRate);

        // Net for Agents
        BigDecimal listingNet = listingGross.subtract(listingGross.multiply(officeRate));
        BigDecimal sellingNet = sellingGross.subtract(sellingGross.multiply(officeRate));

        Commission commission = new Commission();

        commission.setListingId(listingId);
        commission.setLeadId(leadId);

        commission.setOfficeCut(officeCutValue);
        commission.setListingAgentPayout(listingNet);
        commission.setSellingAgentPayout(sellingNet);
        commission.setTotalCommissionValue(totalGross);

        return repository.save(commission);
    }
}
