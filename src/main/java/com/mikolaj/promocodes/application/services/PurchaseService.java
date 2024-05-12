package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.purchase_dtos.CreatePurchaseDto;

public interface PurchaseService {
    Object simulatePurchase(CreatePurchaseDto createPurchaseDto);
}
