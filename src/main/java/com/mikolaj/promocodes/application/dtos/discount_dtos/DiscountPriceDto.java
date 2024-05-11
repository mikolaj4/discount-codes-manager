package com.mikolaj.promocodes.application.dtos.discount_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DiscountPriceDto {
    @Valid

    @NotEmpty(message = "code name is mandatory")
    private String codeName;

    @NotNull(message = "product id is mandatory")
    private Integer productId;
}
