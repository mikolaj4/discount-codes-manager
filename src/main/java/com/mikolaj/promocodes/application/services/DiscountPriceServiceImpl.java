package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ReturnDiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ReturnPromoCodeDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.domain.entity.PromoCode;
import com.mikolaj.promocodes.domain.repository.ProductRepository;
import com.mikolaj.promocodes.domain.repository.PromoCodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiscountPriceServiceImpl implements DiscountPriceService{
    private PriceCalculationService priceCalculationService;

    @Autowired
    public DiscountPriceServiceImpl(PriceCalculationService priceCalculationService) {
        this.priceCalculationService = priceCalculationService;
    }

    @Override
    public ReturnDiscountPriceDto calculateDiscountPrice(DiscountPriceDto discountPriceDto) {
       DiscountDetailsDto discountDetailsDto = priceCalculationService.calculateDiscountPrice(discountPriceDto.getProductId(), discountPriceDto.getCodeName());

       return new ReturnDiscountPriceDto(
               discountDetailsDto.getUpdatedPrice(),
               discountDetailsDto.getCurrency(),
               discountDetailsDto.getMessage(),
               discountDetailsDto.getHttpStatus()
       );
    }


}
