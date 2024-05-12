package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ResponseDiscountPriceDto;

public interface DiscountPriceService {
    ResponseDiscountPriceDto calculateDiscountPrice(DiscountPriceDto discountPriceDto);
}
