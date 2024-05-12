package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ReturnDiscountPriceDto;
import com.mikolaj.promocodes.application.services.DiscountPriceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscountPriceController {

    private DiscountPriceService discountPriceService;

    @Autowired
    public DiscountPriceController(DiscountPriceService discountPriceService) {
        this.discountPriceService = discountPriceService;
    }

    @PostMapping("/discount-price")
    ResponseEntity<ReturnDiscountPriceDto> getDiscountPrice(@Valid @RequestBody DiscountPriceDto discountPriceDto){
        ReturnDiscountPriceDto returnDiscountPriceDto = discountPriceService.calculateDiscountPrice(discountPriceDto);

        return ResponseEntity.status(returnDiscountPriceDto.getHttpStatus()).body(returnDiscountPriceDto);
    }
}
