package com.mikolaj.promocodes.application.dtos.purchase_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ReturnWarningPurchaseDto {
    private Double updatedPrice;
    private String currency;
    private String message;
    private HttpStatus httpStatus;

}

