package com.kotlin.cartcheckoutservice.app.entity

import com.kotlin.cartcheckoutservice.app.entity.UserProduct
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.kotlin.cartcheckoutservice.app.model.UserOrderStatus


@Entity
@Table(name = "orders")
class Order() {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var id: Long = 0

	@get: NotBlank
	var customerId: String = ""

	@get: NotNull
	var totalAmount: Double = 0.0

	@get: NotNull
	var userOrderStatus: UserOrderStatus = UserOrderStatus.NA

	@JsonManagedReference
	@OneToMany(mappedBy = "orders", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
	@get: NotNull
	var productsList: List<UserProduct> = emptyList()
}