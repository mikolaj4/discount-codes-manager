package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.api.exceptions.PromoCodeAlreadyExistsException;
import com.mikolaj.promocodes.api.exceptions.PromoCodeNotFoundException;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ReturnPromoCodeDto;
import com.mikolaj.promocodes.domain.entity.Product;
import com.mikolaj.promocodes.domain.entity.PromoCode;
import com.mikolaj.promocodes.domain.repository.PromoCodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromoCodeServiceImpl implements PromoCodeService{

    private PromoCodeRepository promoCodeRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PromoCodeServiceImpl(PromoCodeRepository promoCodeRepository, ModelMapper modelMapper) {
        this.promoCodeRepository = promoCodeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ReturnPromoCodeDto> findAll() {
        List<PromoCode> promoCodes = promoCodeRepository.findAll();
        return promoCodes.stream()
                .map(promoCode -> modelMapper.map(promoCode, ReturnPromoCodeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReturnPromoCodeDto findByName(String codeName) {
        Optional<PromoCode> result = promoCodeRepository.findById(codeName);
        PromoCode thePromoCode = result.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found with name: " + codeName));
        return modelMapper.map(thePromoCode, ReturnPromoCodeDto.class);
    }

    @Override
    public ReturnPromoCodeDto save(CreatePromoCodeDto createPromoCodeDto) {
        if (promoCodeRepository.existsById(createPromoCodeDto.getName())){
            throw new PromoCodeAlreadyExistsException("Promo code with the same name already exists");
        }

        PromoCode promoCode = modelMapper.map(createPromoCodeDto, PromoCode.class);
        promoCode.setCurrentUsages(0);
        PromoCode savedPromoCode = promoCodeRepository.save(promoCode);
        return modelMapper.map(savedPromoCode, ReturnPromoCodeDto.class);
    }
}
