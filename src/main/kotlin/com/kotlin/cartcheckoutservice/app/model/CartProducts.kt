package com.kotlin.cartcheckoutservice.app.model

import javax.validation.constraints.NotNull


class CartProducts {
	@get: NotNull
	var customerId: String = ""
	
	@get: NotNull
	var cartProducts: List<String> = emptyList()

}