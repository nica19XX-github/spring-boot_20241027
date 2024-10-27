package com.ph.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "transaction_tbl")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long transactionId;

    // Foreign key to ParkingLot entity
    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "lotId", nullable = false)
    private ParkingLot parkingLot;

    // Foreign key to Vehicle entity
    @ManyToOne
    @JoinColumn(name = "license_plate", referencedColumnName = "licensePlate", nullable = false)
    private Vehicle vehicle;

    @Column(nullable = false)
    private Timestamp checkIn;

    private Timestamp checkOut;

    // Example boolean field to represent parking status
    private Boolean isParked;
}