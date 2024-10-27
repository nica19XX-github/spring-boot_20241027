package com.ph.services;

import com.ph.dto.ParkingLotDTO;
import com.ph.dto.TransactionDTO;
import com.ph.dto.VehicleDTO;
import java.util.List;

public interface ParkingService {
    ParkingLotDTO registerParkingLot(ParkingLotDTO parkingLotDTO);
    VehicleDTO registerVehicle(VehicleDTO vehicleDTO);
    TransactionDTO checkInVehicle(Long lotId, String licensePlate);
    TransactionDTO checkOutVehicle(Long lotId, String licensePlate);
    ParkingLotDTO getParkingLotStatus(Long lotId);
    List<VehicleDTO> getAllVehiclesInLot(Long lotId);
}