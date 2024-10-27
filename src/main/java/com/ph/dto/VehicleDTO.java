package com.ph.dto;

import com.ph.entity.VehicleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class VehicleDTO {

    private String licensePlate;
    private VehicleType type;
    private String ownerName;
}