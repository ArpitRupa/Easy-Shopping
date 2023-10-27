package com.fullstackshopping.easyshopping.address.repository;

import com.fullstackshopping.easyshopping.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<List<Address>> findAllByUser_Id(int id);
    Optional<List<Address>> findAllByUser_Username(String username);}