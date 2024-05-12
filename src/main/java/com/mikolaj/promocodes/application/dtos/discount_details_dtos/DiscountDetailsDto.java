package com.mikolaj.promocodes.application.dtos.discount_details_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class DiscountDetailsDto {
    private Integer purchasedProductId;
    private Double originalPrice;
    private Double updatedPrice;
    private Double amountDiscount;
    private String currency;
    private String message;
    private HttpStatus httpStatus;
}
