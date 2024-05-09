package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.product_dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.ReturnProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;

import java.util.List;

public interface ProductService {
    List<ReturnProductDto> findAll();

    ReturnProductDto save(CreateProductDto createProductDto);

    ReturnProductDto update(UpdateProductDto updateProductDto);
}
