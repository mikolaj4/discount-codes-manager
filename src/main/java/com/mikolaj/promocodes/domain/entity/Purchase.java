package com.mikolaj.promocodes.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="regular_price")
    private Double regularPrice;

    @Column(name="amount_discount")
    private Double amountDiscount;

    @Column(name="currency")
    private String currency;

    @JoinColumn(name = "purchased_product_id", referencedColumnName = "id", table = "products")
    private Integer purchasedProductId;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    public Purchase(Double regularPrice, Double amountDiscount, String currency, Integer purchasedProductId, LocalDate purchaseDate) {
        this.regularPrice = regularPrice;
        this.amountDiscount = amountDiscount;
        this.currency = currency;
        this.purchasedProductId = purchasedProductId;
        this.purchaseDate = purchaseDate;
    }
}
