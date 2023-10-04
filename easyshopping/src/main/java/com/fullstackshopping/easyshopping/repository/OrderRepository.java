package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
