package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(CreateProductDto createProductDto);

    Product update(UpdateProductDto updateProductDto);
}
