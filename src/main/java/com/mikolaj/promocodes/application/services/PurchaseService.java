package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.purchase_dtos.CreatePurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ReturnCorrectPurchaseDto;

public interface PurchaseService {
    Object simulatePurchase(CreatePurchaseDto createPurchaseDto);
}
