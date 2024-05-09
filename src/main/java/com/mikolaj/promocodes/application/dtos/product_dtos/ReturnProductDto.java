package com.mikolaj.promocodes.application.dtos.product_dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String currency;
}
