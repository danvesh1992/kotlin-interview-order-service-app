package com.kotlin.cartcheckoutservice.app.entity

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotBlank
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "product")
class Product() {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	var id: Long = 0

	@get: NotBlank
	var type: String = ""

	@get: NotNull
	var price: Int = 0

	@get: NotNull
	var enablePromo: Boolean = false


	@get: NotNull
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "promoId")
	var promoCode: PromoCode? = null


//	@JsonBackReference
//	@OneToMany(mappedBy = "product", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
//	var productsList: List<UserProduct> = emptyList()
}