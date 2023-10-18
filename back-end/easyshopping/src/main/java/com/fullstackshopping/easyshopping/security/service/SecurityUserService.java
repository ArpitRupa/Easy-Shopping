package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.security.model.SecurityUser;
import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service responsible for user details retrieval for authentication in Spring Security.
 */
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

    /**
     * Load user details from the database based on the provided username.
     *
     * @param username The username to retrieve from the database.
     * @return SecurityUser which implements UserDetails containing user information.
     * @throws UsernameNotFoundException if the provided username is not found in the database.
     */
    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // query the database to get username and password
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found."));
        // create and return a SecurityUser with credentials from the database
        return new SecurityUser(user.getUsername(), user.getPassword(), user.getRole());
    }
}
