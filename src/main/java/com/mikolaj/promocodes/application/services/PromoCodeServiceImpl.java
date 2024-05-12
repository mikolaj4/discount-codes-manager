package com.mikolaj.promocodes.application.services;

import com.mikolaj.promocodes.api.exceptions.PromoCodeAlreadyExistsException;
import com.mikolaj.promocodes.api.exceptions.PromoCodeNotFoundException;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.CreatePromoCodeDto;
import com.mikolaj.promocodes.application.dtos.promo_code_dtos.ResponsePromoCodeDto;
import com.mikolaj.promocodes.domain.entity.PromoCode;
import com.mikolaj.promocodes.domain.repository.PromoCodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public List<ResponsePromoCodeDto> findAll() {
        List<PromoCode> promoCodes = promoCodeRepository.findAll();
        return promoCodes.stream()
                .map(promoCode -> modelMapper.map(promoCode, ResponsePromoCodeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponsePromoCodeDto findByName(String codeName) {
        Optional<PromoCode> result = promoCodeRepository.findById(codeName);
        PromoCode thePromoCode = result.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found with name: " + codeName));
        return modelMapper.map(thePromoCode, ResponsePromoCodeDto.class);
    }

    @Override
    public PromoCode findEntityByName(String codeName){
        Optional<PromoCode> result = promoCodeRepository.findById(codeName);
        return result.orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found with name: " + codeName));
    }
    @Override
    public ResponsePromoCodeDto save(CreatePromoCodeDto createPromoCodeDto) {
        if (promoCodeRepository.existsById(createPromoCodeDto.getName())){
            throw new PromoCodeAlreadyExistsException("Promo code with the same name already exists");
        }

        PromoCode promoCode = modelMapper.map(createPromoCodeDto, PromoCode.class);
        LocalDate expDate = LocalDate.parse(createPromoCodeDto.getExpDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        promoCode.setExpDate(expDate);
        promoCode.setCurrentUsages(0);

        PromoCode savedPromoCode = promoCodeRepository.save(promoCode);

        return modelMapper.map(savedPromoCode, ResponsePromoCodeDto.class);
    }

    @Override
    public void findAndUpdateUsages(String codeName) {
        PromoCode promoCode = promoCodeRepository.findById(codeName)
                .orElseThrow(() -> new PromoCodeNotFoundException("Promo code not found with name: " + codeName));

        promoCode.setCurrentUsages(promoCode.getCurrentUsages() + 1);
        promoCodeRepository.save(promoCode);
    }
}
