package com.ph.mapper;

import com.ph.dto.TransactionDTO;
import com.ph.entity.ParkingLot;
import com.ph.entity.Transaction;
import com.ph.entity.Vehicle;

public class TransactionMapper {

    // Convert Transaction to TransactionDTO
    public static TransactionDTO toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setParkingLotId(transaction.getParkingLot().getLotId());
        dto.setLicensePlate(transaction.getVehicle().getLicensePlate());
        dto.setCheckIn(transaction.getCheckIn());
        dto.setCheckOut(transaction.getCheckOut());
        dto.setIsParked(transaction.getIsParked());
        return dto;
    }

    // Convert TransactionDTO to Transaction
    public static Transaction toEntity(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(dto.getTransactionId());

        // Set ParkingLot and Vehicle references
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setLotId(dto.getParkingLotId());
        transaction.setParkingLot(parkingLot);

        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        transaction.setVehicle(vehicle);

        transaction.setCheckIn(dto.getCheckIn());
        transaction.setCheckOut(dto.getCheckOut());
        transaction.setIsParked(dto.getIsParked());
        return transaction;
    }
}