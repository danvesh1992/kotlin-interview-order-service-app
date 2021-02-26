package com.kotlin.cartcheckoutservice.app.entity

import com.kotlin.cartcheckoutservice.app.entity.UserProduct
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.JsonBackReference


@Entity
@Table(name = "promocode")
class PromoCode() {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var promoId: Long = 0

	@get: NotBlank
	var promoCode: String = ""

	@get: NotNull
	var discountedItems: Int = 0


//	@JsonBackReference
//	@OneToMany(mappedBy = "promoCode", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
//	@get: NotNull
//	var productsList: List<UserProduct> = emptyList()
}