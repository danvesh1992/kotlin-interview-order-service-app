package com.kotlin.cartcheckoutservice.app.kafka.config

import org.springframework.beans.factory.annotation.Configurable
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*
import org.apache.kafka.clients.admin.NewTopic

@Configuration
class CartAppKafkaProducerConfiguration {

	@Value("\${kafka.host.localhost}")
	private val bootStraphost: String? = null

	@Value("\${kafka.port.9092}")
	private val port: Int = 0

	@Bean
	fun producerFactory(): ProducerFactory<String, String> {
		val configProps = HashMap<String, Any>()
		configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "$bootStraphost:$port"
		configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
		configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
		return DefaultKafkaProducerFactory(configProps)
	}

	@Bean
	fun kafkaTemplate(): KafkaTemplate<String, String> {
		return KafkaTemplate(producerFactory())
	}

	@Bean
	fun customer_order_status_topic(): NewTopic {
		return NewTopic("cartapp_order", 1, 1);
	}

	companion object {
		const val CARTAPP_ORDER: String = "cartapp_order"
	}

}