package com.lannisterlogics.UserService.service;

import com.lannisterlogics.UserService.exceptions.ResourceNotFoundException;
import com.lannisterlogics.UserService.model.Address;
import com.lannisterlogics.UserService.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public User saveUserWithAddress(User user, Address address);

    public User getUserByBusinessId(Long id) throws ResourceNotFoundException;
}
