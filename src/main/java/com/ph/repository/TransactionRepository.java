package com.ph.repository;

import com.ph.entity.ParkingLot;
import com.ph.entity.Transaction;
import com.ph.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    boolean existsByVehicleAndCheckOutIsNull(Vehicle vehicle);
    boolean existsByParkingLotAndVehicleAndCheckOutIsNull(ParkingLot parkingLot, Vehicle vehicle);
    Transaction findByVehicleAndCheckOutIsNull(Vehicle vehicle);
    List<Transaction> findByParkingLot(ParkingLot parkingLot);
}