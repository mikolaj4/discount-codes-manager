package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.product_dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.ResponseProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;

import java.util.List;

public interface ProductService {
    List<ResponseProductDto> findAll();

    Product findById(Integer productId);

    ResponseProductDto save(CreateProductDto createProductDto);

    ResponseProductDto update(UpdateProductDto updateProductDto);
}
