package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import com.fullstackshopping.easyshopping.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    //password encoder for user passwords
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public SecurityUserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // query the database to get username and password
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found."));
        // create and return a SecurityUser with credentials from the database
        return new SecurityUser(user.getUsername(), user.getPassword(), user.getRole());
    }
}
