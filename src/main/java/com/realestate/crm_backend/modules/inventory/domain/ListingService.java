/*
 * Real Estate CRM - High-performance Real Estate Management Platform
 * Copyright (C) 2025 Leandro Junior Alves dos Santos
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.realestate.crm_backend.modules.inventory.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import com.realestate.crm_backend.modules.inventory.api.ListingDTO;
import com.realestate.crm_backend.modules.inventory.repository.ListingRepository;

@Service
public class ListingService {

    private final ListingRepository repository;

    // Factory to create Geometry points (SRID 4326 = GPS Standard)
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    // Dependency Injection : Best Practice
    public ListingService(ListingRepository repository) {
        this.repository = repository;
    }

    public List<ListingDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ListingDTO createListing(ListingDTO dto) {
        Listing entity = new Listing();
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setAddress(dto.address());
        entity.setBedrooms(dto.bedrooms());
        entity.setBathrooms(dto.bathrooms());
        entity.setAreaSquareMeters(dto.areaSquareMeters());

        // Handle Geometry Conversion
        if (dto.latitude() != null && dto.longitude() != null) {
            Point point = geometryFactory.createPoint(new Coordinate(dto.longitude(), dto.latitude()));
            entity.setLocation(point);
        }

        Listing saved = repository.save(entity);
        return convertToDTO(saved);
    }

    private ListingDTO convertToDTO(Listing entity) {
        Double lat = null;
        Double lon = null;

        if (entity.getLocation() != null) {
            lat = entity.getLocation().getY();
            lon = entity.getLocation().getX();
        }

        return new ListingDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getAddress(),
                entity.getBedrooms(),
                entity.getBathrooms(),
                entity.getAreaSquareMeters(),
                lat,
                lon
        );
    }

}
