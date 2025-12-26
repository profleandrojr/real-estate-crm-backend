package com.realestate.crm_backend.modules.commissions.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.commissions.domain.Commission;
import com.realestate.crm_backend.modules.commissions.domain.CommissionService;

@RestController
@RequestMapping("/api/commissions")
public class CommissionController {

    private final CommissionService service;

    public CommissionController(CommissionService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public ResponseEntity<Commission> process(@RequestBody CommissionRequestDTO dto) {
        Commission result = service.processSale(dto.listingId(), dto.leadId(), dto.salePrice());
        return ResponseEntity.ok(result);
    }

}
