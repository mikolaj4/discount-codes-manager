package com.mikolaj.promocodes.api.controllers;

import com.mikolaj.promocodes.application.dtos.purchase_dtos.CreatePurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ReturnCorrectPurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ReturnWarningPurchaseDto;
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

        if (purchaseResult instanceof ReturnCorrectPurchaseDto returnCorrectPurchaseDto)
        {
            return ResponseEntity.status(returnCorrectPurchaseDto.getHttpStatus()).body(returnCorrectPurchaseDto);
        }
        else if (purchaseResult instanceof ReturnWarningPurchaseDto warningDto)
        {
            return ResponseEntity.status(warningDto.getHttpStatus()).body(warningDto);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response type.");
        }
    }
}
