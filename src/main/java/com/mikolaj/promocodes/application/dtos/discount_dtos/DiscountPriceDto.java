package com.mikolaj.promocodes.application.dtos.discount_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DiscountPriceDto {
    @Valid

    @NotEmpty(message = "code name is mandatory")
    @Size(max = 24, message = "length must be <= 24")
    private String codeName;

    @NotNull(message = "product id is mandatory")
    @Min(value = 1, message = "product indexing starts at 1")
    @Max(value = 9999999, message = "there wont more than 10 million products in the database, cmon...")
    private Integer productId;
}
