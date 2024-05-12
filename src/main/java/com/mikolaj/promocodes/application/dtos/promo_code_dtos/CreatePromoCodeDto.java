package com.mikolaj.promocodes.application.dtos.promo_code_dtos;

import com.mikolaj.promocodes.api.validators.ValidLocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class CreatePromoCodeDto {
    @Valid

    @Pattern(regexp = "^[a-zA-Z0-9]{3,24}$", message = "must me alphanumeric code - size: 3-24 characters")
    private String name;

    @NotNull(message = "discount amount is mandatory")
    @Min(value = 1, message = "must be >= 1")
    @Max(value = 9999999, message = "must be <= 9 999 999")
    private Double discountAmount;


    @Pattern(regexp= "^(PLN|EUR|USD|GBP|CAD)$",
            message = "supported currencies: PLN|EUR|USD|GBP|CAD")
    private String currency;

    @Min(value = 1, message = "must be >= 1")
    @Max(value = 9999999, message = "must be <= 9 999 999")
    @NotNull(message = "max usages is mandatory")
    private Integer maxUsages;

    @ValidLocalDate
    private String expDate;

}
