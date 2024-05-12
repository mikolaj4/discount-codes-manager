package com.mikolaj.promocodes.application.dtos.purchase_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePurchaseDto {
    @Valid

    @NotNull(message = "product id is mandatory")
    @Min(value = 1, message = "product indexing starts at 1")
    @Max(value = 9999999, message = "there wont more than 10 million products in the database, cmon...")
    private Integer productId;

    @Size(max = 24, message = "length must be <= 24")
    private String codeName;
}
