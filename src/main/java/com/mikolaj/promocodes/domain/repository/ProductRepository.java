package com.mikolaj.promocodes.domain.repository;

import com.mikolaj.promocodes.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
