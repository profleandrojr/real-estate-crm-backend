package com.realestate.crm_backend.modules.identity.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.identity.domain.Realtor;
import com.realestate.crm_backend.modules.identity.domain.RealtorService;

@RestController
@RequestMapping("/api/realtors")
public class RealtorController {

    private final RealtorService service;

    public RealtorController(RealtorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RealtorDTO> create(@RequestBody RealtorDTO dto) {
        Realtor entity = new Realtor();
        entity.setName(dto.name());
        entity.setLicenseNumber(dto.licenseNumber());
        entity.setBaseCommissionRate(dto.baseCommissionRate());

        Realtor saved = service.save(entity);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<RealtorDTO>> getAll() {
        List<RealtorDTO> dtos = service.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private RealtorDTO convertToDTO(Realtor entity) {
        return new RealtorDTO(
                entity.getId(),
                entity.getName(),
                entity.getLicenseNumber(),
                entity.getBaseCommissionRate()
        );
    }

}
