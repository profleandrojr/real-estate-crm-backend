package com.realestate.crm_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.crm_backend.dto.ListingDTO;
import com.realestate.crm_backend.service.ListingService;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService service;

    public ListingController(ListingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ListingDTO>> getAllListings() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ListingDTO> createListing(@RequestBody ListingDTO dto) {
        ListingDTO created = service.createListing(dto);
        return ResponseEntity.ok(created);
    }

}
