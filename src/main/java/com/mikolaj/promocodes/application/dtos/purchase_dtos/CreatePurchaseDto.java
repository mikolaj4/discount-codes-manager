package com.mikolaj.promocodes.application.dtos.purchase_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePurchaseDto {
    @Valid

    @NotNull(message = "product id is mandatory")
    private Integer productId;

    private String codeName;
}
