package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.common.dto.request.UpdatePasswordDTO;
import com.fullstackshopping.easyshopping.common.dto.request.UserRegistration;
import com.fullstackshopping.easyshopping.common.dto.response.LoginResponse;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import com.fullstackshopping.easyshopping.user.model.User;
import com.fullstackshopping.easyshopping.user.repository.UserRepository;
import com.fullstackshopping.easyshopping.user.role.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


/**
 * Service responsible for user authentication in Spring Security.
 * Used in AuthenticationController.
 */
@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    /**
     * Constructor to inject dependencies.
     */
    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }



    /**
     * Authenticate a user and generate a JWT token upon successful login.
     *
     * @param username The username provided for login.
     * @param password The password provided for login.
     * @return A LoginResponse with user information and a JWT token.
     * @throws ResponseStatusException if authentication fails.
     */
    public LoginResponse loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            User user = userRepository.findByUsername(username).get();

            return new LoginResponse(user.getUsername(), user.getRole(), token);

        } catch(ResponseStatusException e){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }

    /**
     * Create a new user based on the provided user registration information.
     *
     * @param userRegistration The user registration information including first name, last name, email, username, and password.
     * @return The UserDto representing the newly created user.
     */
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


        try {
            User savedUser = userRepository.save(user);
            return new UserDto(savedUser);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not register user.");

        }
    }

    public UserDto updatePassword(UpdatePasswordDTO updatePasswordDTO, String token) {
        Optional<User> userOptional = userRepository.findByUsername(tokenService.getUsernameFromToken(token));

        // check if optional is not empty
        if (userOptional.isPresent()) {
            String newPassword = updatePasswordDTO.getNewPassword();
            String confirmNewPassword = updatePasswordDTO.getConfirmNewPassword();
            String currentPassword = updatePasswordDTO.getCurrentPassword();
            User user = userOptional.get();

            // check if provided password matches the one in the database
            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current password is incorrect.");
            }

            // check if provided new passwords match
            if (!newPassword.equals(confirmNewPassword)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password and confirm password do not match.");
            }

            //encode the new password
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedNewPassword);
            // Save the updated user in the repository
            userRepository.save(user);
            // return the UserDTO
            return new UserDto(user);

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user to change password.");
        }

    }
}
