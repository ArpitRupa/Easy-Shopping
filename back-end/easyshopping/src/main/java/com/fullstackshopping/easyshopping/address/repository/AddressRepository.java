package com.fullstackshopping.easyshopping.address.repository;

import com.fullstackshopping.easyshopping.address.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByUser_Id(int id);
}