package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ResponseDiscountPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountPriceServiceImpl implements DiscountPriceService{
    private final PriceCalculationService priceCalculationService;

    @Autowired
    public DiscountPriceServiceImpl(PriceCalculationService priceCalculationService) {
        this.priceCalculationService = priceCalculationService;
    }

    @Override
    public ResponseDiscountPriceDto calculateDiscountPrice(DiscountPriceDto discountPriceDto) {
       DiscountDetailsDto discountDetailsDto = priceCalculationService.calculateDiscountPrice(discountPriceDto.getProductId(), discountPriceDto.getCodeName());

       return new ResponseDiscountPriceDto(
               discountDetailsDto.getUpdatedPrice(),
               discountDetailsDto.getCurrency(),
               discountDetailsDto.getMessage(),
               discountDetailsDto.getHttpStatus()
       );
    }


}
