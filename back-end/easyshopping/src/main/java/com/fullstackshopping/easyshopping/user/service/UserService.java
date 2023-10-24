package com.fullstackshopping.easyshopping.user.service;

import com.fullstackshopping.easyshopping.common.dto.request.UserRegistration;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import com.fullstackshopping.easyshopping.security.service.TokenService;
import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.repository.UserRepository;
import com.fullstackshopping.easyshopping.user.role.Role;
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
    private final TokenService tokenService;


    @Autowired //    Constructor Injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }


    // Business logic methods

    /**
     * Retrieve a list of all users in the system.
     *
     * @return A list of UserDto objects, each representing a user.
     */
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

    /**
     * Retrieve a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user information as a UserDto.
     * @throws ResponseStatusException if the user with the specified ID is not found.
     */
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User id not found"));
        return new UserDto(user);
    }

    /**
     * Retrieve a user by their username.
     *
     * @param username The username of the user to retrieve.
     * @return The user information as a UserDto.
     * @throws ResponseStatusException if the user with the specified username is not found.
     */
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));
        return new UserDto(user);
    }

    /**
     * Retrieve a user by their email.
     *
     * @param email The email of the user to retrieve.
     * @return The user information as a UserDto.
     * @throws ResponseStatusException if the user with the specified email is not found.
     */
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "User email not found"));
        return new UserDto(user);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return true if the user was found and deleted, false if the user was not found.
     */
    public boolean deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true; // User was found and deleted.
        } else {
            return false; // User was not found.
        }
    }

    /**
     * Updates an existing user's information.
     *
     * @param id The ID of the user to update.
     * @param updatedUser The updated user information.
     * @return The updated user's information as a UserDto.
     * @throws ResponseStatusException if the user with the specified ID is not found, or if there are conflicts with the updated username or email.
     */
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

        // update columns
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());

        this.userRepository.save(existingUser);

        // Convert the updated user to UserDto and return it
        return new UserDto(existingUser);
    }


    /**
     * Updates the password of a user identified by their ID.
     *
     * @param id The ID of the user to update.
     * @param password The new password to set for the user.
     * @return A {@link UserDto} representing the updated user with the new password.
     * @throws ResponseStatusException if the user with the given ID is not found.
     */
    public UserDto updateUserPassword(int id, String password){

        //get user by id
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }

        existingUser.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(existingUser);

        return new UserDto(existingUser);
    }

    /**
     * Updates the password of a user identified by their ID.
     *
     * @param id The ID of the user to update.
     * @param role The new role for the user.
     * @return A {@link UserDto} representing the updated user with the role.
     * @throws ResponseStatusException if the user with the given ID is not found.
     */

    public UserDto updateUserRole(int id, String role){
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }

        Role roleENum = getRoleFromString(role);
        existingUser.setRole(roleENum);

        this.userRepository.save(existingUser);

        return new UserDto(existingUser);

    }

    /**
     * Converts a role string to a Role enum.
     *
     * @param registrationRole The role string to be converted.
     * @return The corresponding Role enum value.
     * @throws ResponseStatusException if an invalid role is provided.
     */
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

    public UserDto getUser(String token) {
        String username = this.tokenService.getUsernameFromToken(token);

        System.out.println(username);

        User user = this.userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        return new UserDto(user);
    }
}

