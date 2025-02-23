package com.userServiceAuth.service;

import com.userServiceAuth.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
    public User getUserById(String userId);
    public List<User> getAllusers();
    public User updateUser(User user);

    public String deleteUser(String userId);
}
