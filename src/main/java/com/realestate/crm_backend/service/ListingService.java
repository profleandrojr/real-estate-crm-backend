package com.realestate.crm_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import com.realestate.crm_backend.dto.ListingDTO;
import com.realestate.crm_backend.entity.Listing;
import com.realestate.crm_backend.repository.ListingRepository;

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
