package com.ph.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_lots")
@Getter
@Setter
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 50, unique = true, nullable = false)
    private Long lotId;

    @NotBlank(message = "Location is required.")
    private String location;

    @Min(value = 1, message = "Capacity must be greater than zero.")
    private int capacity;

    private int occupiedSpaces;

    private int availableSpots;
}