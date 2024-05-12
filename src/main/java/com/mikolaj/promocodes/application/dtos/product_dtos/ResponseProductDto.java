package com.mikolaj.promocodes.application.dtos.product_dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class ResponseProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private String currency;
}
