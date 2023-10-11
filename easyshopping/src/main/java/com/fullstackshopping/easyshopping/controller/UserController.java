package com.fullstackshopping.easyshopping.controller;

import com.fullstackshopping.easyshopping.dto.response.UserDto;
import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/users","api/users/"})
@CrossOrigin("*")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    //  CRUD methods

//    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("")
    public ResponseEntity< List<UserDto> >getAll(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

//    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = {"/{email}", "/{email}/"})
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

//    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = {"/{username}", "/{username}/"})
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

//    @ResponseStatus(HttpStatus.CREATED) //indicates that something was created in the database
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){ // treat as json

        UserDto userDto = userService.createUser(user);

        URI location = getUserLocation(user.getId());

        return ResponseEntity.created(location).body(userDto);
    }

//    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody User updatedUser){
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public boolean deleteById(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

    private URI getUserLocation (int id){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("users/{id}").buildAndExpand(id).toUri();
    }
}
