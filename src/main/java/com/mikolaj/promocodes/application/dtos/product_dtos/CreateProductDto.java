package com.mikolaj.promocodes.application.dtos.product_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateProductDto {
    @Valid

    @NotEmpty(message = "product name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "product price is mandatory")
    private Double price;

    @NotEmpty(message = "product currency is mandatory")
    private String currency;
}
