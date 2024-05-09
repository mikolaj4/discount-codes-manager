package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ReturnPromoCodeDto;

import java.util.List;

public interface PromoCodeService {

    List<ReturnPromoCodeDto> findAll();

    ReturnPromoCodeDto findByName(String codeName);
    ReturnPromoCodeDto save(CreatePromoCodeDto createPromoCodeDto);
}
