package com.userServiceAuth.service.impl;

import com.userServiceAuth.exception.ResourceNotFoundException;
import com.userServiceAuth.model.User;
import com.userServiceAuth.repository.UserRepository;
import com.userServiceAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()-> new  ResourceNotFoundException("User is not found for this:"+ userId));
    }

    @Override
    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(String userId) {
        return "Deleted successFully..";
    }
}
