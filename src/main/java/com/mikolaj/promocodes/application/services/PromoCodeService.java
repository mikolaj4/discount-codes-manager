package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ReturnPromoCodeDto;
import com.mikolaj.promocodes.domain.entity.PromoCode;

import java.util.List;

public interface PromoCodeService {

    List<ReturnPromoCodeDto> findAll();
    ReturnPromoCodeDto findByName(String codeName);
    PromoCode findEntityByName(String codeName);
    ReturnPromoCodeDto save(CreatePromoCodeDto createPromoCodeDto);
}
