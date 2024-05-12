package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ResponsePromoCodeDto;
import com.mikolaj.promocodes.application.services.PromoCodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PromoCodeController {
    private final PromoCodeService promoCodeService;

    @Autowired
    public PromoCodeController(PromoCodeService thePromoCodeService){promoCodeService = thePromoCodeService;}

    @GetMapping("/codes")
    public ResponseEntity<List<ResponsePromoCodeDto>>  findAll(){
        List<ResponsePromoCodeDto> allPromoCodesDtos = promoCodeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPromoCodesDtos);
    }

    @GetMapping("/codes/{codeName}")
    public ResponseEntity<ResponsePromoCodeDto> getPromoCode(@PathVariable String codeName){
        ResponsePromoCodeDto dbPromoCode = promoCodeService.findByName(codeName);
        return ResponseEntity.status(HttpStatus.OK).body(dbPromoCode);
    }

    @PostMapping("/codes")
    public ResponseEntity<ResponsePromoCodeDto> addPromoCode(@Valid @RequestBody CreatePromoCodeDto createPromoCodeDto){
        ResponsePromoCodeDto dbPromoCode = promoCodeService.save(createPromoCodeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dbPromoCode);
    }
}
