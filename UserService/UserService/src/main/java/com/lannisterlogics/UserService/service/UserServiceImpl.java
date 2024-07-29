package com.lannisterlogics.UserService.service;

import com.lannisterlogics.UserService.exceptions.ResourceNotFoundException;
import com.lannisterlogics.UserService.model.Address;
import com.lannisterlogics.UserService.repository.AddressRepository;
import com.lannisterlogics.UserService.repository.UserRepository;
import com.lannisterlogics.UserService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUserWithAddress(User user, Address address) {
        // Save address first
        Address savedAddress = addressRepository.save(address);

        // Set address to user
        user.setAddress(savedAddress);

        // Save user with reference to address
        return userRepository.save(user);
    }

    @Override
    public User getUserByBusinessId(Long id) throws ResourceNotFoundException {
        Optional<User> userOptional = userRepository.findByBusinessId(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
}
