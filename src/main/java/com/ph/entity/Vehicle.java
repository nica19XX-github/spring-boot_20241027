package com.ph.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class Vehicle {

    @Id
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "License Plate can contain letters, numbers, and dashes only.")
    @NotBlank(message = "License Plate is required.")
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Owner Name can contain letters and spaces only.")
    @NotBlank(message = "Owner Name is required.")
    private String ownerName;

}