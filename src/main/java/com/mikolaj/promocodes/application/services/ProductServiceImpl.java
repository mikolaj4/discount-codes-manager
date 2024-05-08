package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.api.exceptions.ProductNotFoundException;
import com.mikolaj.promocodes.domain.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository, ModelMapper theModelMapper){
        productRepository = theProductRepository;
        modelMapper = theModelMapper;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(CreateProductDto createProductDto){
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setId(0);   // set id=0 to create new product with auto generated id

        return productRepository.save(product);
    }

    @Override
    public Product update(UpdateProductDto updateProductDto) {
        int productIdFromDto = updateProductDto.getId();
        Optional<Product> optionalProduct = productRepository.findById(productIdFromDto);

        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            modelMapper.map(updateProductDto, existingProduct);
            return productRepository.save(existingProduct);
        }else {
            throw new ProductNotFoundException("Product with ID " + productIdFromDto + " not found");
        }

    }
}
