package com.userServiceAuth.service.impl;

import com.userServiceAuth.exception.ResourceNotFoundException;
import com.userServiceAuth.model.User;
import com.userServiceAuth.repository.UserRepository;
import com.userServiceAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    // Get user by ID with caching
    @Cacheable(value = "/v1/api/users", key = "#userId")
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User is not found for this:"+ userId));
    }

    @Override
    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    @Override
    // Update user and refresh cache

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(String userId) {
        return "Deleted successFully..";
    }
}
