package com.lannisterlogics.UserService.controller;

import com.lannisterlogics.UserService.exceptions.ResourceNotFoundException;
import com.lannisterlogics.UserService.model.Address;
import com.lannisterlogics.UserService.model.User;
import com.lannisterlogics.UserService.model.UserRequest;
import com.lannisterlogics.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUserWithAddress(@RequestBody UserRequest userRequest) {
        try {
            System.out.println(userRequest);

            // Create User entity
            User user = new User();
            user.setBusinessId(userRequest.getBusinessId());
            user.setPlatformId(userRequest.getPlatformId());
            user.setBusinessName(userRequest.getBusinessName());
            user.setRevenue(userRequest.getRevenue());
            user.setFounderName(userRequest.getFounderName());
            user.setDOB(userRequest.getDOB());
            user.setLegalStructure(userRequest.getLegalStructure());
            user.setContact(userRequest.getContact());
            user.setBusinessEmail(userRequest.getBusinessEmail());

            // Create Address entity
            Address address = new Address();
            address.setPostalCode(userRequest.getPostalCode());
            address.setHouseNo(userRequest.getHouseNo());
            address.setCountry(userRequest.getCountry());
            address.setYearsOfLiving(userRequest.getYearsOfLiving());

            // Save user and address
            userService.saveUserWithAddress(user, address);

            return ResponseEntity.ok("User successfully created.");
        } catch (DataIntegrityViolationException e) {
            // Handle duplicate entry error
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Duplicate businessId. A user with this businessId already exists.");
        } catch (Exception e) {
            // Handle any other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        try {
            User user = userService.getUserByBusinessId(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
