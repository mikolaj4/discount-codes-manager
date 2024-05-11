package com.mikolaj.promocodes.application.dtos.promo_code_dtos;

import com.mikolaj.promocodes.api.validators.ValidLocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePromoCodeDto {
    @Valid

    @Pattern(regexp = "^[a-zA-Z0-9]{3,24}$", message = "must me alphanumeric code - size: 3-24 characters")
    private String name;

    @NotNull(message = "discount amount is mandatory")
    private Double discountAmount;

    @NotEmpty(message = "code currency is mandatory")
    private String currency;

    @Min(value = 1, message = "must be >= 1")
    @Max(value = 9999999, message = "must be <= 9999999")
    @NotNull(message = "max usages is mandatory")
    private Integer maxUsages;

    @ValidLocalDate
    private String expDate;

}
