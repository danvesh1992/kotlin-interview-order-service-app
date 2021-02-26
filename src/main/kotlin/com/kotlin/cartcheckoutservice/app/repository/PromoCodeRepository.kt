package com.kotlin.cartcheckoutservice.app.repository

import com.kotlin.cartcheckoutservice.app.entity.PromoCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PromoCodeRepository : JpaRepository<PromoCode, Long> {
}