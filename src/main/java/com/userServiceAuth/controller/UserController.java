package com.userServiceAuth.controller;

import com.userServiceAuth.model.User;
import com.userServiceAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
     User createduser=   userService.createUser(user);
     return ResponseEntity.ok(createduser);
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") String userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllusers();
    }
}
