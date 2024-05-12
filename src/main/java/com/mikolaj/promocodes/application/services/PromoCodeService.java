package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ResponsePromoCodeDto;
import com.mikolaj.promocodes.domain.entity.PromoCode;

import java.util.List;

public interface PromoCodeService {

    List<ResponsePromoCodeDto> findAll();
    ResponsePromoCodeDto findByName(String codeName);
    PromoCode findEntityByName(String codeName);
    ResponsePromoCodeDto save(CreatePromoCodeDto createPromoCodeDto);
    void findAndUpdateUsages(String codeName);
}
