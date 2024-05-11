package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.application.dtos.discount_dtos.DiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.discount_dtos.ReturnDiscountPriceDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ReturnPromoCodeDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.domain.entity.PromoCode;
import com.mikolaj.promocodes.domain.repository.ProductRepository;
import com.mikolaj.promocodes.domain.repository.PromoCodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiscountPriceServiceImpl implements DiscountPriceService{

    private PromoCodeService promoCodeService;
    private ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public DiscountPriceServiceImpl(PromoCodeService promoCodeService, ProductService productService, ModelMapper modelMapper) {
        this.promoCodeService = promoCodeService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReturnDiscountPriceDto calculateDiscountPrice(DiscountPriceDto discountPriceDto) {
        PromoCode promoCode = promoCodeService.findEntityByName(discountPriceDto.getCodeName());
        Product product = productService.findById(discountPriceDto.getProductId());


        double productPrice = product.getPrice();

        LocalDate currentDate = LocalDate.now();
        if (promoCode.getExpDate().isBefore(currentDate)){
            return createReturnDiscountPriceDto(
                    productPrice,
                    product.getCurrency(),
                    "Promo code expired.",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (!product.getCurrency().equals(promoCode.getCurrency())) {
            return createReturnDiscountPriceDto(
                    productPrice,
                    null,
                    "Currencies do not match." +
                    " Product price currency: " + product.getCurrency() +
                    " Promo code currency: " + promoCode.getCurrency(),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (promoCode.getCurrentUsages() >= promoCode.getMaxUsages()){
            return createReturnDiscountPriceDto(
                    productPrice,
                    product.getCurrency(),
                    "Maximum number of code usages reached.",
                    HttpStatus.BAD_REQUEST
            );
        }

        double updatedPrice = productPrice - promoCode.getDiscountAmount();
        if (updatedPrice < 0){
            return createReturnDiscountPriceDto(
                    0,
                    product.getCurrency(),
                    "Price after discount was negative.",
                    HttpStatus.BAD_REQUEST
            );
        }

        return createReturnDiscountPriceDto(
                updatedPrice,
                product.getCurrency(),
                "Discount applied successfully.",
                HttpStatus.OK
        );
    }

    private ReturnDiscountPriceDto createReturnDiscountPriceDto(double price, String currency, String message, HttpStatus status) {
        ReturnDiscountPriceDto returnDiscountPriceDto = new ReturnDiscountPriceDto();
        returnDiscountPriceDto.setUpdatedPrice(price);
        returnDiscountPriceDto.setCurrency(currency);
        returnDiscountPriceDto.setMessage(message);
        returnDiscountPriceDto.setStatus(status);
        return returnDiscountPriceDto;
    }
}
