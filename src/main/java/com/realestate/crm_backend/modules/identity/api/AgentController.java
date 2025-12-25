package com.realestate.crm_backend.modules.identity.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.identity.domain.Agent;
import com.realestate.crm_backend.modules.identity.domain.AgentService;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgentDTO> create(@RequestBody AgentDTO dto) {
        Agent entity = new Agent();
        entity.setName(dto.name());
        entity.setLicenseNumber(dto.licenseNumber());
        entity.setBaseCommissionRate(dto.baseCommissionRate());
        entity.setRealtor(dto.isRealtor());

        Agent saved = service.save(entity);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    @GetMapping
    public ResponseEntity<List<AgentDTO>> getAll() {
        List<AgentDTO> dtos = service.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private AgentDTO convertToDTO(Agent entity) {
        return new AgentDTO(
                entity.getId(),
                entity.getName(),
                entity.getLicenseNumber(),
                entity.getBaseCommissionRate(),
                entity.isRealtor()
        );
    }

}
