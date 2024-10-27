package com.ph.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnResponseForPostEndpoint<T> {

    private T data;
    private int transactionStatusCode;
    private String transactionStatusDescription;

}