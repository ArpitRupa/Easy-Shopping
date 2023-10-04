package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
