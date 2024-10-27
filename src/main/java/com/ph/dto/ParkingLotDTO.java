package com.ph.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParkingLotDTO {
    private long lotId;
    private String location;
    private int capacity;
    private int occupiedSpaces;
    private int availableSpots;
}