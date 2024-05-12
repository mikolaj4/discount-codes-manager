package com.mikolaj.promocodes.domain.repository;

import com.mikolaj.promocodes.domain.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query("SELECT " +
                "p.currency AS currency, " +
                "SUM(p.regularPrice - p.amountDiscount) AS totalAmount, " +
                "SUM(p.amountDiscount) AS totalDiscount, " +
                "COUNT(p) AS purchaseCount " +
            "FROM Purchase p " +
            "GROUP BY p.currency")
    List<Map<String, Object>> generateSalesReport();
}
