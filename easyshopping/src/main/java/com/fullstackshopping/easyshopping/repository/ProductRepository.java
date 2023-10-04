package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
