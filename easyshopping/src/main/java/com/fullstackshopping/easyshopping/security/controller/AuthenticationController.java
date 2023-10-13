package com.fullstackshopping.easyshopping.security.controller;

import com.fullstackshopping.easyshopping.common.dto.request.LoginRequest;
import com.fullstackshopping.easyshopping.common.dto.response.LoginResponse;
import com.fullstackshopping.easyshopping.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/auth","/auth/"})
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value={"/login", "/login/"})
    public LoginResponse loginUser (@RequestBody LoginRequest loginRequest){
        return authenticationService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

}
