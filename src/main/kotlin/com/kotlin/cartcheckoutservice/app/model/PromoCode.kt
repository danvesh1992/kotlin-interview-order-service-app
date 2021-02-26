//package com.kotlin.cartcheckoutservice.app.model
//
//import com.kotlin.cartcheckoutservice.app.entity.Product
//
//enum class PromoCode(val discount: Int) {
//
//	BUYONEGETONE(2),
//	BUYTWOGETONE(3);
//
//
//	companion object {
//		fun applyPromoCode(product: Product, totalProductCount: Int): Int {
//			var promoCodes = values()
//			var selectedPromoCode: PromoCode? = null
//			for (promoCode in promoCodes) {
//				if (promoCode.name.equals(product.promoCode?.promoCode)) {
//					selectedPromoCode = promoCode
//					break
//				}
//			}
//			var productDiscount = 0
//
//			if (selectedPromoCode != null && product.enablePromo) {
//				val discountNumber = selectedPromoCode.discount
//				productDiscount = (totalProductCount / discountNumber) * product.price
//			}
//			return productDiscount
//		}
//	}
//}