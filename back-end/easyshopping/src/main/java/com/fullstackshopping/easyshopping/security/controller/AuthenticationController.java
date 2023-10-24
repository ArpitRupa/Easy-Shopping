package com.fullstackshopping.easyshopping.security.controller;

import com.fullstackshopping.easyshopping.common.dto.request.LoginRequest;
import com.fullstackshopping.easyshopping.common.dto.request.UpdatePasswordDTO;
import com.fullstackshopping.easyshopping.common.dto.request.UserRegistration;
import com.fullstackshopping.easyshopping.common.dto.response.LoginResponse;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import com.fullstackshopping.easyshopping.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistration userRegistration){ // treat as json

        UserDto userDto = authenticationService.createUser(userRegistration);

        URI location = getUserLocation(userDto.getId());

        return ResponseEntity.created(location).body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginRequest loginRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return ResponseEntity.ok().headers(headers).body(authenticationService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }



    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping ("/update-password")
    public ResponseEntity<UserDto> updatePassword (@RequestBody UpdatePasswordDTO updatePasswordDTO, @RequestHeader(name = "Authorization") String token){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return ResponseEntity.ok().headers(headers).body(authenticationService.updatePassword(updatePasswordDTO,token));
    }

    private URI getUserLocation (int id){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("users/{id}").buildAndExpand(id).toUri();
    }
}
