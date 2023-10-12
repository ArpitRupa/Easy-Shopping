package com.fullstackshopping.easyshopping.security.model;

import com.fullstackshopping.easyshopping.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class SecurityUser implements UserDetails {

    private String username;
    private String password;
    private GrantedAuthority authority;

    public SecurityUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.authority = new SimpleGrantedAuthority(role.getRoleName()) ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public GrantedAuthority getAuthority() {
        return this.authority;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }
}
