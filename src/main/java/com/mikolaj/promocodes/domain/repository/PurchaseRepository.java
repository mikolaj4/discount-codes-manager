package com.mikolaj.promocodes.domain.repository;

import com.mikolaj.promocodes.domain.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
