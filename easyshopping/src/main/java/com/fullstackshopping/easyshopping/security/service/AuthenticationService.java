package com.fullstackshopping.easyshopping.security.service;

import com.fullstackshopping.easyshopping.dto.response.LoginResponseDto;
import com.fullstackshopping.easyshopping.dto.response.UserDto;
import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;


    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    public LoginResponseDto loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            User user = userRepository.findByUsername(username).get();

            return new LoginResponseDto(user.getUsername(), user.getRole(), token);

        } catch(ResponseStatusException e){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", e);
        }
    }
}
