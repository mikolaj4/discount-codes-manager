package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.domain.entity.PromoCode;

public interface PriceCalculationService {
    DiscountDetailsDto calculateDiscountPrice(Integer productId, String promoCodeName);
}
