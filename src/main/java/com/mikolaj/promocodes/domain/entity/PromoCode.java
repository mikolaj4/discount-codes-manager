package com.mikolaj.promocodes.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="promo_codes")
public class PromoCode {
    @Id
    @Column(name="id")
    private String name;

    @Column(name="discount_amount")
    private Double discountAmount;

    @Column(name="currency")
    private String currency;

    @Column(name="max_usages")
    private Integer maxUsages;

    @Column(name="current_usages")
    private Integer currentUsages;


}
