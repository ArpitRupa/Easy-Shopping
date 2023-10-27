package com.fullstackshopping.easyshopping.address.service;

import com.fullstackshopping.easyshopping.address.model.Address;
import com.fullstackshopping.easyshopping.address.repository.AddressRepository;
import com.fullstackshopping.easyshopping.common.dto.request.CreateAddress;
import com.fullstackshopping.easyshopping.common.dto.response.ResponseAddress;
import com.fullstackshopping.easyshopping.security.service.TokenService;
import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private  final TokenService tokenService;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserRepository userRepository, TokenService tokenService) {
        this.addressRepository = addressRepository;
        this.userRepository=userRepository;
        this.tokenService = tokenService;
    }


    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public ResponseAddress getAddressById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Address id not found"));
        return new ResponseAddress(address);
    }

    public List<ResponseAddress> getAddressByUserId(int id) {

        List<Address> addresses = this.addressRepository.findAllByUser_Id(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User Id not found"));
        List<ResponseAddress> responseAddresses = new ArrayList<>();

        for (Address address : addresses)
        {
            responseAddresses.add(new ResponseAddress(address));
        }

        return responseAddresses;
    }

    public ResponseAddress createAddress(CreateAddress address, String tokenHeader) {

        String username = this.tokenService.getUsernameFromToken(tokenHeader);

        User user = userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        Address newAddress = new Address(
                address.getShippingAddressLine1(),
                address.getShippingAddressLine2(),
                address.getCity(),
                address.getStateName(),
                address.getPostalCode(),
                user
        );

        try {
            Address savedAddress = addressRepository.save(newAddress);
            return new ResponseAddress(savedAddress);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create address.");

        }

    }


    public List<ResponseAddress> getAddressByUser(String token) {
        String username = this.tokenService.getUsernameFromToken(token);

        List<Address> addresses = addressRepository.findAllByUser_Username(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));
        List<ResponseAddress> responseAddresses = new ArrayList<>();

        for (Address address : addresses)
        {
            responseAddresses.add(new ResponseAddress(address));
        }

        return responseAddresses;
    }

    public boolean deleteUser(int id) {
        try {
            this.addressRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find address to delete.");
        }
    }

    public ResponseAddress updateAddress(int id, CreateAddress newAddress) {

        Address address = this.addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address id not found"));

        address.setShippingAddressLine1(newAddress.getShippingAddressLine1());
        address.setShippingAddressLine2(newAddress.getShippingAddressLine2());
        address.setCity(newAddress.getCity());
        address.setStateName(newAddress.getStateName());
        address.setPostalCode(newAddress.getPostalCode());

        this.addressRepository.save(address);

        return new ResponseAddress(address);
    }
}
