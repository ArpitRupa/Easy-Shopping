package com.fullstackshopping.easyshopping.address.controller;

import com.fullstackshopping.easyshopping.address.model.Address;
import com.fullstackshopping.easyshopping.address.service.AddressService;
import com.fullstackshopping.easyshopping.common.dto.request.CreateAddress;
import com.fullstackshopping.easyshopping.common.dto.response.ResponseAddress;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Address>> getAllAddresses(){

        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAddress> getAddressById(@PathVariable int id){

        return ResponseEntity.ok(addressService.getAddressById(id));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PostMapping("/user/{id}")
    public ResponseEntity<List<ResponseAddress>> getAddressByUserId(@PathVariable int id){

        return ResponseEntity.ok(addressService.getAddressByUserId(id));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/user")
    public ResponseEntity<List<ResponseAddress>> getAddressForUser(@RequestHeader(name = "Authorization") String token){

        return ResponseEntity.ok(addressService.getAddressByUser(token));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PostMapping("/create")
    public ResponseEntity<ResponseAddress> createAddress(@RequestBody CreateAddress address, @RequestHeader(name="Authorization") String token){ // treat as json

        ResponseAddress responseAddress = addressService.createAddress(address, token);

        URI location = getUserLocation(responseAddress.getAddressId());

        return ResponseEntity.created(location).body(responseAddress);
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping({"/{id}"})
    public ResponseEntity<ResponseAddress> updateAddress(@PathVariable int id, @RequestBody CreateAddress address){

        return ResponseEntity.ok(addressService.updateAddress(id, address ));
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @DeleteMapping({"/{id}"})
    public boolean deleteById(@PathVariable int id){
        return this.addressService.deleteUser(id);
    }


    private URI getUserLocation (int id){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("addresses/{id}").buildAndExpand(id).toUri();
    }
}
