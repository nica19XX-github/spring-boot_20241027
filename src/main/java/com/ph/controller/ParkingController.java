package com.ph.controller;

import com.ph.dto.ParkingLotDTO;
import com.ph.dto.TransactionDTO;
import com.ph.dto.VehicleDTO;
import com.ph.response.ReturnResponseForPostEndpoint;
import com.ph.services.ParkingServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingServiceImpl parkingServiceImpl;

    @Autowired
    public ParkingController(ParkingServiceImpl parkingServiceImpl) {
        this.parkingServiceImpl = parkingServiceImpl;
    }

    @PostMapping("/lot")
    public ResponseEntity<ReturnResponseForPostEndpoint<ParkingLotDTO>> registerParkingLot(
            @Valid @RequestBody ParkingLotDTO parkingLotDTO) {
        ParkingLotDTO savedParkingLot = parkingServiceImpl.registerParkingLot(parkingLotDTO);
        ReturnResponseForPostEndpoint<ParkingLotDTO> response = new ReturnResponseForPostEndpoint<>(
                savedParkingLot,
                HttpStatus.CREATED.value(),
                "Parking Lot is successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/vehicle")
    public ResponseEntity<ReturnResponseForPostEndpoint<VehicleDTO>> registerVehicle(
            @Valid @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO savedVehicle = parkingServiceImpl.registerVehicle(vehicleDTO);
        ReturnResponseForPostEndpoint<VehicleDTO> response = new ReturnResponseForPostEndpoint<>(
                savedVehicle,
                HttpStatus.CREATED.value(),
                "Vehicle is successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/checkin")
    public ResponseEntity<ReturnResponseForPostEndpoint<TransactionDTO>> checkInVehicle(
            @RequestParam Long parkingLotId, @RequestParam String licensePlate) {
        TransactionDTO transactionDTO = parkingServiceImpl.checkInVehicle(parkingLotId, licensePlate);
        ReturnResponseForPostEndpoint<TransactionDTO> response = new ReturnResponseForPostEndpoint<>(
                transactionDTO,
                HttpStatus.OK.value(),
                "Vehicle checked in successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<ReturnResponseForPostEndpoint<TransactionDTO>> checkOutVehicle(
            @RequestParam Long parkingLotId, @RequestParam String licensePlate) {
        TransactionDTO transactionDTO = parkingServiceImpl.checkOutVehicle(parkingLotId, licensePlate);
        ReturnResponseForPostEndpoint<TransactionDTO> response = new ReturnResponseForPostEndpoint<>(
                transactionDTO,
                HttpStatus.OK.value(),
                "Vehicle checked out successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/lot/{id}/status")
    public ResponseEntity<ParkingLotDTO> getParkingLotStatus(@PathVariable("id") Long parkingLotId) {
        ParkingLotDTO parkingLotDTO = parkingServiceImpl.getParkingLotStatus(parkingLotId);
        return new ResponseEntity<>(parkingLotDTO, HttpStatus.OK);
    }

    @GetMapping("/lot/{id}/vehicles")
    public ResponseEntity<List<VehicleDTO>> getAllVehiclesInLot(@PathVariable("id") Long parkingLotId) {
        List<VehicleDTO> vehicles = parkingServiceImpl.getAllVehiclesInLot(parkingLotId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
}