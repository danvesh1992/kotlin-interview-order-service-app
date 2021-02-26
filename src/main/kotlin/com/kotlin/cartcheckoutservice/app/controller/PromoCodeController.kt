package com.kotlin.cartcheckoutservice.app.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.GetMapping

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.http.HttpStatus
import com.kotlin.cartcheckoutservice.app.repository.PromoCodeRepository
import com.kotlin.cartcheckoutservice.app.entity.PromoCode
import javax.validation.Valid

@RestController
@RequestMapping("/cartapp/promocode")
class PromoCodeController(@Autowired private val promoCodeRepository: PromoCodeRepository) {

	@GetMapping
	fun getAllPromoCodes(): List<PromoCode> = promoCodeRepository.findAll()

	@PostMapping
	fun createPromoCode(@Valid @RequestBody promoCode: PromoCode): PromoCode = promoCodeRepository.save(promoCode)
}