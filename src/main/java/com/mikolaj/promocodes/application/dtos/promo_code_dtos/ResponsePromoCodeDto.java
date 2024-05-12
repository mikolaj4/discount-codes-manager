package com.mikolaj.promocodes.application.dtos.promo_code_dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ResponsePromoCodeDto {
    private String name;
    private Double discountAmount;
    private String currency;
    private Integer maxUsages;
    private Integer currentUsages;
    private String expDate;

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
