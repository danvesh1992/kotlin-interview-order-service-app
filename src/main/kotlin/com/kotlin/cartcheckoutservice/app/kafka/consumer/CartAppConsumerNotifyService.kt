package com.kotlin.cartcheckoutservice.app.kafka.consumer

import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import com.kotlin.cartcheckoutservice.app.kafka.config.CartAppKafkaProducerConfiguration


class CartAppConsumerNotifyService {
	companion object {
		private val LOGGER = LoggerFactory.getLogger(CartAppConsumerNotifyService::class.java)
	}

	@KafkaListener(topics = [CartAppKafkaProducerConfiguration.CARTAPP_ORDER])
	fun receive(payload: String) {
		LOGGER.info(">>>>>>>>>>>>> payload= >>>>>>>>>>>>>>>>>>>>>>>> '$payload'")
	}

}