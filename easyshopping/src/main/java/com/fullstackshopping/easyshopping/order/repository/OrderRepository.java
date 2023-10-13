package com.fullstackshopping.easyshopping.order.repository;

import com.fullstackshopping.easyshopping.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
