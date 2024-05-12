package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.product_dtos.CreateProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.ResponseProductDto;
import com.mikolaj.promocodes.application.dtos.product_dtos.UpdateProductDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.api.exceptions.ProductNotFoundException;
import com.mikolaj.promocodes.domain.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository, ModelMapper theModelMapper){
        productRepository = theProductRepository;
        modelMapper = theModelMapper;
    }

    @Override
    public List<ResponseProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ResponseProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        } else {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
    }

    @Override
    public ResponseProductDto save(CreateProductDto createProductDto){
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setId(0);   // set id=0 to create new product with auto generated id
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ResponseProductDto.class);
    }

    @Override
    public ResponseProductDto update(UpdateProductDto updateProductDto) {
        int productIdFromDto = updateProductDto.getId();
        Optional<Product> optionalProduct = productRepository.findById(productIdFromDto);

        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            modelMapper.map(updateProductDto, existingProduct);
            Product updatedProduct = productRepository.save(existingProduct);
            return modelMapper.map(updatedProduct, ResponseProductDto.class);
        }else {
            throw new ProductNotFoundException("Product with ID " + productIdFromDto + " not found");
        }

    }
}
