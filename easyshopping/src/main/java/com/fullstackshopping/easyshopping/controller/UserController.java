package com.fullstackshopping.easyshopping.controller;

import com.fullstackshopping.easyshopping.model.User;
import com.fullstackshopping.easyshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/api/users","api/users/"})
public class UserController {

    private final UserRepository userRepository;

    @Autowired // dependency injection
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //  CRUD methods

    @GetMapping("")
    public List<User> getAll(){
        return this.userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = {"/{id}", "/{id}/"})
    public User getUserById(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return user
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User id: " + id + " not found"));
    }

    @ResponseStatus(HttpStatus.CREATED) //indicates that something was created in the database
    @PostMapping("")
    public void createUser(@RequestBody User user){ // treat as json
        this.userRepository.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = {"/{id}", "/{id}/"})
    public void updateUser(@PathVariable int id, @RequestBody User updatedUser){
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
        existingUser.setPassword(updatedUser.getPassword());

        this.userRepository.save(existingUser);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public void deleteById(@PathVariable int id){
        // check to see if entry exists before deleting
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");
        }

        this.userRepository.deleteById(id);
    }

}
