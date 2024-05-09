package com.mikolaj.promocodes.domain.repository;

import com.mikolaj.promocodes.domain.entity.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoCodeRepository extends JpaRepository<PromoCode, String> {
}
