package com.fullstackshopping.easyshopping.product.repository;

import com.fullstackshopping.easyshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findAllBySellerId(int id);
}
