package com.kotlin.cartcheckoutservice.app.entity

import com.kotlin.cartcheckoutservice.app.entity.Order
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "userproduct")
class UserProduct {


	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var id: Long = 0

	@get: NotNull
	var totalAmount: Int = 0

	@get: NotNull
	var actualAmount: Int = 0

	@get: NotNull
	var discountAmount: Int = 0

	@get: NotNull
	var productCount: Int = 0


	//@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	var orders: Order? = null
	
//	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	@get: NotNull
	var product: Product? = null
}