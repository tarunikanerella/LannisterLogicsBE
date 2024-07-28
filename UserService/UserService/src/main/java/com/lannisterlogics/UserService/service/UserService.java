package com.lannisterlogics.UserService.service;

import com.lannisterlogics.UserService.exceptions.ResourceNotFoundException;
import com.lannisterlogics.UserService.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User createUser(User user);

    public User getUserById(Long id) throws ResourceNotFoundException;
}
