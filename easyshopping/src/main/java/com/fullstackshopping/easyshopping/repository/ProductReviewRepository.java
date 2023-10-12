package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
}
