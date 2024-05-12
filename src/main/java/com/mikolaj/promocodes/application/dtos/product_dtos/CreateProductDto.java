package com.mikolaj.promocodes.application.dtos.product_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class CreateProductDto {
    @Valid

    @NotBlank(message = "product name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9 ]{3,64}$",
                message = "Product name can only contain letters, numbers, and spaces. Must be between 3 and 64 characters.")
    private String name;

    @Size(max = 255,
            message = "description cannot exceed 255 characters")
    private String description;

    @NotNull(message = "product price is mandatory")
    @Min(value = 1, message = "minimum product price: 1")
    @Max(value = 9999999, message = "maximum product price is 9999999")
    private Double price;

    @Pattern(regexp= "^(PLN|EUR|USD|GBP|CAD)$",
            message = "supported currencies: PLN|EUR|USD|GBP|CAD")
    private String currency;
}
