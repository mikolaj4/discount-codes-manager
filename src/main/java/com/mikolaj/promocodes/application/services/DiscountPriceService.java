package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ReturnDiscountPriceDto;

public interface DiscountPriceService {
    ReturnDiscountPriceDto calculateDiscountPrice(DiscountPriceDto discountPriceDto);
}
