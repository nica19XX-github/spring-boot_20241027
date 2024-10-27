package com.ph.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {

    private Long transactionId;
    private Long parkingLotId;
    private String licensePlate;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private Boolean isParked;
}