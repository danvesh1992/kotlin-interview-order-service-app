package com.kotlin.cartcheckoutservice.app.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.kotlin.cartcheckoutservice.app.service.UserOrderService
import com.kotlin.cartcheckoutservice.app.entity.Order
import com.kotlin.cartcheckoutservice.app.model.CartProducts
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import javax.validation.Valid

@RestController
@RequestMapping("/cartapp/order")
class CartCheckoutController(@Autowired private val userOrderService: UserOrderService) {


	@GetMapping
	fun getAllOrders(): List<Order> = userOrderService.getAllOrders()

	@PostMapping
	fun createUserOrder(@Valid @RequestBody cart: CartProducts): ResponseEntity<Any> {
		if (cart.cartProducts.isEmpty()) {
			return ResponseEntity("cart products not found", HttpStatus.BAD_REQUEST)
		}
		var userOrder: Order = userOrderService.createOrder(cart)
		return ResponseEntity(userOrder, HttpStatus.CREATED)
	}


}