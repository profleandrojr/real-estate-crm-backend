package com.realestate.crm_backend.modules.identity.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.modules.identity.domain.Agent;
import com.realestate.crm_backend.modules.identity.domain.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AgentDTO> create(@Valid @RequestBody AgentDTO dto) {

        Agent entity = Agent.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .licenseNumber(dto.licenseNumber())
                .baseCommissionRate(dto.baseCommissionRate())
                .isRealtor(dto.isRealtor())
                .isActive(true)
                .build();

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

    @PostMapping("/{id}/toggle-status")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
        service.toggleStatus(id); // You'll need to add this method to AgentService
        return ResponseEntity.ok().build();
    }

    private AgentDTO convertToDTO(Agent entity) {
        return new AgentDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getLicenseNumber(),
                entity.getBaseCommissionRate(),
                entity.isRealtor(),
                entity.isActive()
        );
    }

}
