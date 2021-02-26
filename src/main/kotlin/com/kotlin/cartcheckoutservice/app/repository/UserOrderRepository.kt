package com.kotlin.cartcheckoutservice.app.repository

import com.kotlin.cartcheckoutservice.app.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserOrderRepository : JpaRepository<Order, Long> {
}