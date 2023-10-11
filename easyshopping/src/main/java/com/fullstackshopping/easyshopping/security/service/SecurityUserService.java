package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public SecurityUserService(UserRepository userRepository){ this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In user details!");

        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found."));
    }
}
