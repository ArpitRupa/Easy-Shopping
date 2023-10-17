package com.fullstackshopping.easyshopping.productimage.repository;

import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
