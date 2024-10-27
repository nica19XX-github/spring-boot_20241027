package com.ph.services;

import com.ph.dto.ParkingLotDTO;
import com.ph.dto.TransactionDTO;
import com.ph.dto.VehicleDTO;
import com.ph.entity.ParkingLot;
import com.ph.entity.Transaction;
import com.ph.entity.Vehicle;
import com.ph.mapper.ParkingLotMapper;
import com.ph.mapper.TransactionMapper;
import com.ph.mapper.VehicleMapper;
import com.ph.repository.ParkingLotRepository;
import com.ph.repository.TransactionRepository;
import com.ph.repository.VehicleRepository;
import com.ph.response.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public ParkingLotDTO registerParkingLot(ParkingLotDTO parkingLotDTO) {
        // Check if a parking lot with the same location already exists
        if (parkingLotRepository.existsByLocation(parkingLotDTO.getLocation())) {
            throw new RuntimeException("A parking lot with this location already exists.");
        }

        // Map DTO to entity and initialize fields
        ParkingLot lot = ParkingLotMapper.toEntity(parkingLotDTO);
        lot.setOccupiedSpaces(0); // Initialize to 0
        lot.setAvailableSpots(parkingLotDTO.getCapacity());

        // Save the new parking lot
        ParkingLot savedParkingLot = parkingLotRepository.save(lot);
        return ParkingLotMapper.toDTO(savedParkingLot);
    }

    @Override
    public VehicleDTO registerVehicle (VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleMapper.toEntity(vehicleDTO);
        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(saveVehicle);
    }

    @Override
    public TransactionDTO checkInVehicle(Long parkingLotId, String licensePlate) {
        // Find the parking lot
        ParkingLot parkingLot = parkingLotRepository.findById(String.valueOf(parkingLotId))
                .orElseThrow(() -> new ResourceNotFoundException("Parking Lot not found"));

        // Find the vehicle
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        // Check if the vehicle is already parked in any lot
        if (transactionRepository.existsByVehicleAndCheckOutIsNull(vehicle)) {
            throw new ResourceNotFoundException("Vehicle is already parked in another lot.");
        }

        // Check if there are available parking spots
        if (parkingLot.getAvailableSpots() <= 0) {
            throw new ResourceNotFoundException("No available spots in this parking lot.");
        }

        // Check if parking lot is full based on capacity
        if (parkingLot.getOccupiedSpaces() >= parkingLot.getCapacity()) {
            throw new ResourceNotFoundException("Parking lot is full.");
        }

        // Check for duplicate lotId and licensePlate per check-in
        if (transactionRepository.existsByParkingLotAndVehicleAndCheckOutIsNull(parkingLot, vehicle)) {
            throw new ResourceNotFoundException("This vehicle is already checked in at the specified parking lot.");
        }

        // Create a new transaction for the check-in
        Transaction transaction = createTransaction(parkingLot, vehicle);

        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Update the parking lot's occupied and available spots
        updateParkingLot(parkingLot);

        // Convert the saved transaction to DTO and return it
        return TransactionMapper.toDto(savedTransaction);
    }

    @Override
    public TransactionDTO checkOutVehicle(Long parkingLotId, String licensePlate) {
        // Find the parking lot
        ParkingLot parkingLot = parkingLotRepository.findById(String.valueOf(parkingLotId))
                .orElseThrow(() -> new ResourceNotFoundException("Parking Lot not found"));

        // Find the vehicle
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        // Find the transaction that matches the vehicle and has not been checked out yet
        Transaction transaction = transactionRepository.findByVehicleAndCheckOutIsNull(vehicle);
        if (transaction == null) {
            throw new RuntimeException("Vehicle is not currently checked in.");
        }

        // Set the check-out time
        transaction.setCheckOut(Timestamp.valueOf(LocalDateTime.now()));
        // Mark the vehicle as not parked anymore
        transaction.setIsParked(false);

        // Save the updated transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Update the parking lot's occupied and available spots
        updateParkingLotAfterCheckOut(parkingLot);

        // Convert the saved transaction to DTO and return it
        return TransactionMapper.toDto(savedTransaction);
    }

    @Override
    public ParkingLotDTO getParkingLotStatus(Long lotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(String.valueOf(lotId))
                .orElseThrow(() -> new ResourceNotFoundException("Parking Lot not found"));

        ParkingLotDTO parkingLotDTO = new ParkingLotDTO();
        parkingLotDTO.setLotId(parkingLot.getLotId());
        parkingLotDTO.setLocation(parkingLot.getLocation());
        parkingLotDTO.setCapacity(parkingLot.getCapacity());
        parkingLotDTO.setOccupiedSpaces(parkingLot.getOccupiedSpaces());
        parkingLotDTO.setAvailableSpots(parkingLot.getAvailableSpots());
        return parkingLotDTO;
    }

    @Override
    public List<VehicleDTO> getAllVehiclesInLot(Long lotId) {

        ParkingLot parkingLot = parkingLotRepository.findById(String.valueOf(lotId))
                .orElseThrow(() -> new ResourceNotFoundException("Parking Lot not found"));

        List<Transaction> transactions = transactionRepository.findByParkingLot(parkingLot);

        // Filter transactions with no check-out time, map vehicles to DTOs
        List<VehicleDTO> vehiclesInLot = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCheckOut() == null) { // Vehicle is currently parked
                Vehicle vehicle = transaction.getVehicle();
                VehicleDTO vehicleDTO = new VehicleDTO();
                vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
                vehicleDTO.setType(vehicle.getType());
                vehicleDTO.setOwnerName(vehicle.getOwnerName());
                // Set additional fields as necessary
                vehiclesInLot.add(vehicleDTO);
            }
        }
        return vehiclesInLot;
    }


    private void updateParkingLotAfterCheckOut(ParkingLot parkingLot) {
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() - 1);
        parkingLot.setAvailableSpots(parkingLot.getAvailableSpots() + 1);
        parkingLotRepository.save(parkingLot);
    }

    private Transaction createTransaction(ParkingLot parkingLot, Vehicle vehicle) {
        Transaction transaction = new Transaction();
        transaction.setParkingLot(parkingLot);
        transaction.setVehicle(vehicle);
        transaction.setCheckIn(Timestamp.valueOf(LocalDateTime.now()));
        transaction.setIsParked(true);
        return transaction;
    }

    private void updateParkingLot(ParkingLot parkingLot) {
        parkingLot.setOccupiedSpaces(parkingLot.getOccupiedSpaces() + 1);
        parkingLot.setAvailableSpots(parkingLot.getAvailableSpots() - 1);
        parkingLotRepository.save(parkingLot);
    }


}