package com.fullstackshopping.easyshopping.security.controller;

import com.fullstackshopping.easyshopping.common.dto.request.LoginRequest;
import com.fullstackshopping.easyshopping.common.dto.response.LoginResponse;
import com.fullstackshopping.easyshopping.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser (@RequestBody LoginRequest loginRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        return ResponseEntity.ok().headers(headers).body(authenticationService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()));
    }

}
