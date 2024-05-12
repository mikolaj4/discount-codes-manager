package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.application.dtos.product_dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.ReturnProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.application.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService theProductService){
        productService = theProductService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ReturnProductDto>> findAll(){
        List<ReturnProductDto> allProductsDtos = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allProductsDtos);
    }

    @PostMapping("/products")
    public ResponseEntity<ReturnProductDto> addProduct(@Valid @RequestBody CreateProductDto createProductDto){
        ReturnProductDto dbProductDto = productService.save(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbProductDto);
    }

    @PutMapping("/products")
    public ResponseEntity<ReturnProductDto> updateProduct(@Valid @RequestBody UpdateProductDto updateProductDto){
        ReturnProductDto dbProductDto = productService.update(updateProductDto);
        return ResponseEntity.status(HttpStatus.OK).body(dbProductDto);
    }

}










