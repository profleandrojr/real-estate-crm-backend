package com.realestate.crm_backend.modules.identity.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "First-name is required")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last-name is required")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = false, precision = 5, scale = 2)
    @Min(value = 0, message = "Commission rate cannot be negative")
    @Max(value = 100, message = "Commission rate cannot exceed 100%")
    private BigDecimal baseCommissionRate;

    @Column(nullable = false)
    @Builder.Default
    private boolean isRealtor = false;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}
