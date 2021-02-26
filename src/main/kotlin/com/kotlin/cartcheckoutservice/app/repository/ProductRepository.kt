package com.kotlin.cartcheckoutservice.app.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.kotlin.cartcheckoutservice.app.entity.Product

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
	
	fun findByType(type : String) : Product
}