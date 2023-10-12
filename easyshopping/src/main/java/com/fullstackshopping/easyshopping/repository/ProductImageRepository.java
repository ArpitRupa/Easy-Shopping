package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
