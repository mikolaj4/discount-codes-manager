package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.application.dtos.purchase_dtos.CreatePurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ResponseCorrectPurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ResponseWarningPurchaseDto;
import com.mikolaj.promocodes.application.services.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    ResponseEntity<?> simulatePurchase(@Valid @RequestBody CreatePurchaseDto createPurchaseDto){
        Object purchaseResult = purchaseService.simulatePurchase(createPurchaseDto);

        if (purchaseResult instanceof ResponseCorrectPurchaseDto responseCorrectPurchaseDto)
        {
            return ResponseEntity.status(responseCorrectPurchaseDto.getHttpStatus()).body(responseCorrectPurchaseDto);
        }
        else if (purchaseResult instanceof ResponseWarningPurchaseDto warningDto)
        {
            return ResponseEntity.status(warningDto.getHttpStatus()).body(warningDto);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type.");
        }
    }
}
