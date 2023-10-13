package com.fullstackshopping.easyshopping.productreview.repository;

import com.fullstackshopping.easyshopping.productreview.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
}
