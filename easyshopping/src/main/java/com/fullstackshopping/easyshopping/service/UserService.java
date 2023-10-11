package com.fullstackshopping.easyshopping.service;

import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;
    @Autowired //    Dependency Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // Business logic methods
    public User createUser(User user) {
        // You can add validation or other business logic here
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }


    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}

