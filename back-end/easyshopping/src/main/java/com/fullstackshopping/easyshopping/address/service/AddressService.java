package com.fullstackshopping.easyshopping.address.service;

import com.fullstackshopping.easyshopping.address.model.Address;
import com.fullstackshopping.easyshopping.address.repository.AddressRepository;
import com.fullstackshopping.easyshopping.common.dto.request.CreateAddress;
import com.fullstackshopping.easyshopping.common.dto.response.ResponseAddress;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import com.fullstackshopping.easyshopping.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public ResponseAddress getAddressById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Address id not found"));
        return new ResponseAddress(address);
    }

    public List<ResponseAddress> getAddressByUserId(int id) {
        List<Address> addresses = this.addressRepository.findAllByUser_Id(id);
        List<ResponseAddress> responseAddresses = new ArrayList<>();

        for (Address address : addresses)
        {
            responseAddresses.add(new ResponseAddress(address));
        }

        return responseAddresses;
    }

    public ResponseAddress createAddress(CreateAddress address) {

        Address newAddress = new Address(
                address.getShippingAddressLine1(),
                address.getShippingAddressLine2(),
                address.getCity(),
                address.getStateName(),
                address.getPostalCode()
        );

        try {
            Address savedAddress = addressRepository.save(newAddress);
            return new ResponseAddress(savedAddress);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not register user.");

        }

    }
}
