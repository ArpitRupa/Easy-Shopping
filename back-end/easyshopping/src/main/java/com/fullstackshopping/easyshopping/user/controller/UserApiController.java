package com.fullstackshopping.easyshopping.user.controller;

import com.fullstackshopping.easyshopping.common.dto.request.UserRegistration;
import com.fullstackshopping.easyshopping.common.dto.response.UserDto;
import com.fullstackshopping.easyshopping.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {


    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService){
        this.userService=userService;
    }

    //  CRUD methods

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity< List<UserDto> >getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/user")
    public ResponseEntity<UserDto> getUser(@RequestHeader(name = "Authorization") String token){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return ResponseEntity.ok().headers(headers).body(userService.getUser(token));
    }


    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){

        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistration userRegistration){ // treat as json

        UserDto userDto = userService.createUser(userRegistration);

        URI location = getUserLocation(userDto.getId());

        return ResponseEntity.created(location).body(userDto);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping({"/{id}"})
    public ResponseEntity<UserDto> updateUserInformation(@PathVariable int id, @RequestBody UserRegistration updatedUser){
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/{id}/role"})
    public ResponseEntity<UserDto> updateUserRole(@PathVariable int id, @RequestBody String role){
        return ResponseEntity.ok(userService.updateUserRole(id, role));
    }

    //update user password
    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER'))")
    @PutMapping({"/{id}/password"})
    public ResponseEntity<UserDto> updateUserPassword(@PathVariable int id, @RequestBody String password){
        return ResponseEntity.ok(userService.updateUserPassword(id, password));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/{id}"})
    public boolean deleteById(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

    private URI getUserLocation (int id){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("users/{id}").buildAndExpand(id).toUri();
    }
}
