package com.ph.mapper;

import com.ph.dto.ParkingLotDTO;
import com.ph.entity.ParkingLot;

public class ParkingLotMapper {

    public static ParkingLot toEntity(ParkingLotDTO dto) {
        ParkingLot lot = new ParkingLot();
        lot.setLotId(dto.getLotId());
        lot.setLocation(dto.getLocation());
        lot.setCapacity(dto.getCapacity());
        lot.setOccupiedSpaces(dto.getOccupiedSpaces());
        lot.setAvailableSpots(dto.getAvailableSpots());
        return lot;
    }

    public static ParkingLotDTO toDTO(ParkingLot lot) {
        ParkingLotDTO dto = new ParkingLotDTO();
        dto.setLotId(lot.getLotId());
        dto.setLocation(lot.getLocation());
        dto.setCapacity(lot.getCapacity());
        dto.setOccupiedSpaces(lot.getOccupiedSpaces());
        dto.setAvailableSpots(lot.getAvailableSpots());
        return dto;
    }
}