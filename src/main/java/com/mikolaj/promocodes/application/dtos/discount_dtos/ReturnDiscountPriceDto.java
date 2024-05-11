package com.mikolaj.promocodes.application.dtos.discount_dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ReturnDiscountPriceDto {
    private Double updatedPrice;
    private String currency;
    private String message;
    private HttpStatus status;
}
