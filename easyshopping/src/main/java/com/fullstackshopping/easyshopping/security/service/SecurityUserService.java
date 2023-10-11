package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import com.fullstackshopping.easyshopping.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserService(UserRepository userRepository){ this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found."));

        return new SecurityUser(user.getUsername(), user.getPassword(), user.getRole());
    }
}
