package com.kotlin.cartcheckoutservice.app.kafka.config


import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import java.util.*
import com.kotlin.cartcheckoutservice.app.kafka.consumer.CartAppConsumerNotifyService

@EnableKafka
@Configuration
class CartAppKafkaConsumerTopicConfig {

	@Value("\${kafka.host.localhost}")
	private val bootStraphost: String? = null

	@Value("\${kafka.port.9092}")
	private val port: Int = 0

	@Bean
	fun consumerConfigs(): Map<String, Any> {
		val props = HashMap<String, Any>()
		props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "$bootStraphost:$port"
		props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
		props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
		props[ConsumerConfig.GROUP_ID_CONFIG] = "notificationGroup"
		return props
	}

	@Bean
	fun consumerFactory(): ConsumerFactory<String, String> {
		return DefaultKafkaConsumerFactory(consumerConfigs())
	}

	@Bean
	fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> {
		val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
		factory.consumerFactory = consumerFactory()
		return factory
	}

	@Bean
	fun kafkaConsumer(): CartAppConsumerNotifyService {
		return CartAppConsumerNotifyService()
	}

}