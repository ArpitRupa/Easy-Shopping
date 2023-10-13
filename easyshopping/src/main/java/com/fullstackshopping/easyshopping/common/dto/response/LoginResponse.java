package com.fullstackshopping.easyshopping.common.dto.response;

import com.fullstackshopping.easyshopping.user.role.Role;

public class LoginResponse {


    private String username;
    private Role role;
    private String jwt;

    public LoginResponse(){

    }

    public LoginResponse(String username, Role role, String jwt){
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
