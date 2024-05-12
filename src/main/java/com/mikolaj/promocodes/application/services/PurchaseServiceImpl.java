package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.CreatePurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ResponseCorrectPurchaseDto;
import com.mikolaj.promocodes.application.dtos.purchase_dtos.ResponseWarningPurchaseDto;
import com.mikolaj.promocodes.domain.entity.Purchase;
import com.mikolaj.promocodes.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PriceCalculationService priceCalculationService;
    private final PromoCodeService promoCodeService;
    private final PurchaseRepository purchaseRepository;


    @Autowired
    public PurchaseServiceImpl(PriceCalculationService priceCalculationService, PromoCodeService promoCodeService, PurchaseRepository purchaseRepository) {
        this.priceCalculationService = priceCalculationService;
        this.promoCodeService = promoCodeService;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Object simulatePurchase(CreatePurchaseDto createPurchaseDto) {

        DiscountDetailsDto discountDetailsDto = priceCalculationService.calculateDiscountPrice(createPurchaseDto.getProductId(), createPurchaseDto.getCodeName());

        // case : purchase successful - adding to database
        if (discountDetailsDto.getHttpStatus() == HttpStatus.OK){

            //case : purchase successful and promo code was used - incrementing code usages
            if (createPurchaseDto.getCodeName() != null){
                promoCodeService.findAndUpdateUsages(createPurchaseDto.getCodeName());
            }

            Purchase purchaseToSave = new Purchase(
                    discountDetailsDto.getOriginalPrice(),
                    discountDetailsDto.getAmountDiscount(),
                    discountDetailsDto.getCurrency(),
                    discountDetailsDto.getPurchasedProductId(),
                    LocalDate.now()
            );
            Purchase savedPurchase = savePurchase(purchaseToSave);

            return new ResponseCorrectPurchaseDto(
                    savedPurchase.getId(),
                    savedPurchase.getRegularPrice(),
                    savedPurchase.getAmountDiscount(),
                    savedPurchase.getCurrency(),
                    savedPurchase.getPurchasedProductId(),
                    discountDetailsDto.getHttpStatus(),
                    discountDetailsDto.getMessage()
            );
        }
        //case - rules for applying discount not met - returning regular price with appropriate warning message
        else {
            return new ResponseWarningPurchaseDto(
                    discountDetailsDto.getUpdatedPrice(),
                    discountDetailsDto.getCurrency(),
                    discountDetailsDto.getMessage(),
                    discountDetailsDto.getHttpStatus()
            );
        }
    }

    private Purchase savePurchase(Purchase purchaseToSave){
        return purchaseRepository.save(purchaseToSave);
    }
}
