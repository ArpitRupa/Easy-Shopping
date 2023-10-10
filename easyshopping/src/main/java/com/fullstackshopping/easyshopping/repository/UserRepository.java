package com.fullstackshopping.easyshopping.repository;

import com.fullstackshopping.easyshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    /**
     * This is an abstract method that performs some operation.
     *
     * @param username username to use in query to find user
     * @return User object obtained from the database
     */
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
