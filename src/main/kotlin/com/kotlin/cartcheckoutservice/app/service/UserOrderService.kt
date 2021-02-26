package com.kotlin.cartcheckoutservice.app.service


import java.util.Properties

import java.time.Duration
import com.kotlin.cartcheckoutservice.app.entity.PromoCode

import com.kotlin.cartcheckoutservice.app.exception.GenericServiceException
import org.springframework.stereotype.Service
import com.kotlin.cartcheckoutservice.app.repository.UserOrderRepository
import org.springframework.beans.factory.annotation.Autowired
import com.kotlin.cartcheckoutservice.app.entity.Order
import com.kotlin.cartcheckoutservice.app.model.CartProducts
import com.kotlin.cartcheckoutservice.app.repository.ProductRepository
import com.kotlin.cartcheckoutservice.app.entity.Product
import com.kotlin.cartcheckoutservice.app.entity.UserProduct
import org.springframework.dao.EmptyResultDataAccessException
import com.kotlin.cartcheckoutservice.app.model.UserOrderStatus
import org.slf4j.LoggerFactory
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item

@Service
class UserOrderService(
	@Autowired private val promoService: PromoService,
	@Autowired private val userOrderRepository: UserOrderRepository,
	@Autowired private val productRepository: ProductRepository
) {

	companion object {
		private val LOGGER = LoggerFactory.getLogger(UserOrderService::class.java)
	}

	fun getAllOrders(): List<Order> = userOrderRepository.findAll()

	fun createOrder(cartProduct: CartProducts): Order {

		val productsList: MutableList<UserProduct> = mutableListOf()
		val userOrder: Order = Order()
		userOrder.customerId = cartProduct.customerId
		userOrder.userOrderStatus = UserOrderStatus.NA

		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> Order is beginning for given cart >>>>>>>>>>>>>>>>>>>>>>. $cartProduct")
		var cartAmount: Double = 0.0

		val cartProducts = cartProduct.cartProducts.groupingBy { it.apply {} }.eachCount()

		for ((cartPrdct, count) in cartProducts) {
			var selectedProduct: Product? = null;
			try {
				LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>> Retreiving product >>>>>>>>>>>>>>>>>>>>>>>>")
				selectedProduct = productRepository.findByType(cartPrdct)
			} catch (e: EmptyResultDataAccessException) {
			}

			if (selectedProduct === null) {
				LOGGER.error(">>>>>>>>>>>>>>>>>>>>>>>> Invalid cart product Found. >>>>>>>>>>>>>>>>>>>>>>>>")
				throw GenericServiceException("Invalid Product: $cartProduct")
			}
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> Adding $count $cartProduct(s) to the order >>>>>>>>>>>>>>>>>>>>>>>>")
			val regularAmount = count * selectedProduct.price
			val promoAmount = promoService.getDiscount(selectedProduct, count)

			val itemTotalAmount = (regularAmount - promoAmount)
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> Applied a promoAmount of $promoAmount on $selectedProduct >>>>>>>>>>>>>>>>>>>>>>>>")

			val userProduct: UserProduct = UserProduct()
			userProduct.totalAmount = itemTotalAmount
			userProduct.actualAmount = regularAmount
			userProduct.discountAmount = promoAmount
			userProduct.productCount = count
			userProduct.orders = userOrder
			userProduct.product = selectedProduct

			productsList.add(userProduct)

			cartAmount = cartAmount + itemTotalAmount
		}

		userOrder.totalAmount = cartAmount / 100
		userOrder.productsList = productsList
		userOrder.userOrderStatus = UserOrderStatus.PLACED

		userOrderRepository.save(userOrder)

		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>> Order placed succesfully >>>>>>>>>>>>>>>>>>>>>>>> $userOrder")
		return userOrder
	}

}


