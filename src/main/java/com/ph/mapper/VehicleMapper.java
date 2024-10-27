package com.ph.mapper;

import com.ph.dto.VehicleDTO;
import com.ph.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public static Vehicle toEntity(VehicleDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setType(dto.getType());
        vehicle.setOwnerName(dto.getOwnerName());
        return vehicle;
    }

    public static VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setType(vehicle.getType());
        dto.setOwnerName(vehicle.getOwnerName());
        return dto;
    }
}