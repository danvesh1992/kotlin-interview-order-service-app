package com.kotlin.cartcheckoutservice.app.kafka.producer

import org.springframework.beans.factory.annotation.Autowired
import com.kotlin.cartcheckoutservice.app.kafka.config.CartAppKafkaProducerConfiguration
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import com.kotlin.cartcheckoutservice.app.entity.Order
import org.slf4j.LoggerFactory

@Service
class CartProducerNotifyService(@Autowired private val kafkaTemplate: KafkaTemplate<String, String>) {

	companion object {
		private val LOGGER = LoggerFactory.getLogger(CartProducerNotifyService::class.java)
	}

	fun sendOrderStatus(order: Order): Boolean {
		val mapper: ObjectMapper = ObjectMapper()
		try {
			kafkaTemplate.send(CartAppKafkaProducerConfiguration.CARTAPP_ORDER, mapper.writeValueAsString(order))
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> cart app order finished successfully $order>>>>>>>>>>>>>>>>>>>>>>>>>>>")
		} catch (e: Exception) {
			return false
		}
		return true
	}
}