package com.kotlin.cartcheckoutservice.app

import  com.kotlin.cartcheckoutservice.app.service.UserOrderService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CartCheckOutApp

fun main(args: Array<String>) {
	runApplication<CartCheckOutApp>(*args)	
}