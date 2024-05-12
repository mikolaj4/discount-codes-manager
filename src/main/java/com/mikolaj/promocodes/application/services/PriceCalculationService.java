package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;

public interface PriceCalculationService {
    DiscountDetailsDto calculateDiscountPrice(Integer productId, String promoCodeName);
}
