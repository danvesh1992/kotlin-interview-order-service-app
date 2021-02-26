package com.kotlin.cartcheckoutservice.app.service

import org.springframework.stereotype.Service
import  com.kotlin.cartcheckoutservice.app.entity.Product
import  com.kotlin.cartcheckoutservice.app.entity.PromoCode


@Service
class PromoService {
	fun getDiscount(product: Product, totalProductCount: Int): Int {
		var selectedPromoCode: PromoCode? = product.promoCode
		var productDiscount = 0
		if (selectedPromoCode != null && product.enablePromo) {
			val discountedItem = selectedPromoCode.discountedItems
			productDiscount = (totalProductCount / discountedItem) * product.price
		}
		return productDiscount
	}
}