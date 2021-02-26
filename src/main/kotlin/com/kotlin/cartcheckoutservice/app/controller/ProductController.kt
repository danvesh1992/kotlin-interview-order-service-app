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
import com.kotlin.cartcheckoutservice.app.repository.ProductRepository
import com.kotlin.cartcheckoutservice.app.entity.Product
import javax.validation.Valid
import org.springframework.http.MediaType


@RestController
@RequestMapping("/cartapp/product")
class ProductController(@Autowired private val productRepository: ProductRepository) {

	@GetMapping
	fun getAllProducts(): List<Product> = productRepository.findAll()


	@PostMapping(consumes = ["application/json"])
	fun createProduct(@Valid @RequestBody product: Product): Product = productRepository.save(product)


	@GetMapping("/{productId}")
	fun getProductById(@PathVariable productId: Long): ResponseEntity<Product> =
		productRepository.findById(productId).map {
			ResponseEntity.ok(it)
		}.orElse(ResponseEntity.notFound().build())


	@DeleteMapping("/{productId}")
	fun deleteProduct(@PathVariable productId: Long): ResponseEntity<Void> =
		productRepository.findById(productId).map {
			productRepository.delete(it)
			ResponseEntity<Void>(HttpStatus.OK)
		}.orElse(ResponseEntity.notFound().build())
}