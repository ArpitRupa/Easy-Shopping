package com.fullstackshopping.easyshopping.product.repository;

import com.fullstackshopping.easyshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
