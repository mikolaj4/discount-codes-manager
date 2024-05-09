package com.mikolaj.promocodes.application.dtos.promo_code_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreatePromoCodeDto {
    @Valid

    @NotEmpty(message = "code name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,24}$")
    private String name;

    @NotNull(message = "discount amount is mandatory")
    private Double discountAmount;

    @NotEmpty(message = "code currency is mandatory")
    private String currency;

    @Min(1)
    @NotNull(message = "max usages is mandatory")
    private Integer maxUsages;



}
