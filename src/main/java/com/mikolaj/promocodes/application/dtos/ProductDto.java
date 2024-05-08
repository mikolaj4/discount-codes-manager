package com.mikolaj.promocodes.application.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String currency;
}
