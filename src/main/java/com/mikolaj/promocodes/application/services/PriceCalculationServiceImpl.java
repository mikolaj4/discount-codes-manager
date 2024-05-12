package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_details_dtos.DiscountDetailsDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.domain.entity.PromoCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PriceCalculationServiceImpl implements PriceCalculationService{
    private final ProductService productService;
    private final PromoCodeService promoCodeService;

    public PriceCalculationServiceImpl(ProductService productService, PromoCodeService promoCodeService) {
        this.productService = productService;
        this.promoCodeService = promoCodeService;
    }

    @Override
    public DiscountDetailsDto calculateDiscountPrice(Integer productId, String promoCodeName) {

        Product product = productService.findById(productId);

        double productPrice = product.getPrice();
        // case : purchase without promo code
        if (promoCodeName == null){
            return new DiscountDetailsDto(
                    product.getId(),
                    productPrice,
                    productPrice,
                    0.0,
                    product.getCurrency(),
                    "Purchase successful, but discount code was not used.",
                    HttpStatus.OK
            );
        }

        PromoCode promoCode = promoCodeService.findEntityByName(promoCodeName);

        LocalDate currentDate = LocalDate.now();
        // case : promo code is expired
        if (promoCode.getExpDate().isBefore(currentDate)){
            return new DiscountDetailsDto(
                    product.getId(),
                    productPrice,
                    productPrice,
                    0.0,
                    product.getCurrency(),
                    "Promo code expired.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // case : promo code applies to different currency than product
        if (!product.getCurrency().equals(promoCode.getCurrency())) {
            return new DiscountDetailsDto(
                    product.getId(),
                    productPrice,
                    productPrice,
                    0.0,
                    null,
                    "Currencies do not match." +
                            " Product price currency: " + product.getCurrency() +
                            " Promo code currency: " + promoCode.getCurrency(),
                    HttpStatus.BAD_REQUEST
            );
        }

        // case : promo code has reached usage limit
        if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()){
            return new DiscountDetailsDto(
                    product.getId(),
                    productPrice,
                    productPrice,
                    0.0,
                    product.getCurrency(),
                    "Maximum number of code usages reached.",
                    HttpStatus.BAD_REQUEST
            );
        }


        double updatedPrice = productPrice - promoCode.getDiscountAmount();
        // case : product price after applying discount is below 0
        if (updatedPrice < 0){
            return new DiscountDetailsDto(
                    product.getId(),
                    productPrice,
                    0.0,
                    promoCode.getDiscountAmount(),
                    product.getCurrency(),
                    "Price after discount was negative.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // case : promo code can be applied to product
        return new DiscountDetailsDto(
                product.getId(),
                productPrice,
                updatedPrice,
                promoCode.getDiscountAmount(),
                product.getCurrency(),
                "Discount applied successfully.",
                HttpStatus.OK
        );

    }
}
