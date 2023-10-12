package com.fullstackshopping.easyshopping.dto.response;

import com.fullstackshopping.easyshopping.model.enums.Role;

public class LoginResponseDto {


    private String username;
    private Role role;
    private String jwt;

    public LoginResponseDto(){

    }

    public LoginResponseDto(String username, Role role, String jwt){
        this.username = username;
        this.role = role;
        this.jwt = jwt;
    }

    public String getUsername() {
        return this.username;
    }

    public Role getRole() {
        return this.role;
    }

    public String getJwt() {
        return this.jwt;
    }
}
