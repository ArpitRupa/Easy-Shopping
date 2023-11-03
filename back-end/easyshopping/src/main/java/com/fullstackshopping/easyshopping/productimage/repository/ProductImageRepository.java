package com.fullstackshopping.easyshopping.productimage.repository;

import com.fullstackshopping.easyshopping.productimage.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

    Optional<List<ProductImage>> findAllByProduct_Id(int id);
}
