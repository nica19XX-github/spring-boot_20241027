package com.ph.repository;

import com.ph.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    boolean existsByLocation(String location);
}