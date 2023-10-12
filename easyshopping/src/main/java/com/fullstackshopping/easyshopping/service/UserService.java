package com.fullstackshopping.easyshopping.service;

import com.fullstackshopping.easyshopping.dto.request.UserRegistration;
import com.fullstackshopping.easyshopping.dto.response.UserDto;
import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.model.enums.Role;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired //    Constructor Injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // Business logic methods
    public UserDto createUser(UserRegistration userRegistration) {


        // Create user from Registration
        User user = new User(
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getEmail(),
                userRegistration.getUsername(),
                //encode password before saving to database
                passwordEncoder.encode(userRegistration.getPassword()),
                // default all new users to USER role
                Role.USER
        );

        // Continue with user creation
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //list to hold all DTOs
        List<UserDto> userDtos = new ArrayList<>();

        //generate DTOs for all users
        for (User user : users) {
            userDtos.add( new UserDto(user));
        }

        return userDtos;
    }

    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found"));
        return new UserDto(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));
        return new UserDto(user);
    }


    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User email not found"));
        return new UserDto(user);
    }


    public boolean deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true; // User was found and deleted.
        } else {
            return false; // User was not found.
        }
    }


    @Transactional(rollbackOn = ResponseStatusException.class)
    public UserDto updateUser(int id, UserRegistration updatedUser) {

        // check to see if entry exists before updating
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }

        // check if the updated username conflicts with existing users
        if (!existingUser.getUsername().equals(updatedUser.getUsername()) &&
                userRepository.existsByUsername(updatedUser.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        // check if the updated email conflicts with existing users
        if (!existingUser.getEmail().equals(updatedUser.getEmail()) &&
                userRepository.existsByEmail(updatedUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        // update other columns
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        existingUser.setRole(getRoleFromString(updatedUser.getRole()));

        this.userRepository.save(existingUser);

        // Convert the updated user to UserDto and return it
        return new UserDto(existingUser);
    }


    private Role getRoleFromString(String registrationRole){
        // Convert the role string to a Role enum
        Role role;
        try {
            role = Role.valueOf(registrationRole);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid role provided"
            );
        }

        return role;
    }
}

