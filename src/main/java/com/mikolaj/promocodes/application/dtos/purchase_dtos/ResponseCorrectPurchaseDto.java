package com.mikolaj.promocodes.application.dtos.purchase_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCorrectPurchaseDto {
    private Integer id;
    private Double regularPrice;
    private Double amountDiscount;
    private String currency;
    private Integer purchasedProductId;
    private HttpStatus httpStatus;
    private String message;

}
