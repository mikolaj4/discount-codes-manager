package com.mikolaj.promocodes.application.dtos.promo_code_dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnPromoCodeDto {
    private String name;
    private Double discountAmount;
    private String currency;
    private Integer maxUsages;
    private Integer currentUsages;

}
