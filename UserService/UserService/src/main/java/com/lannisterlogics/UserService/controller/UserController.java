package com.lannisterlogics.UserService.controller;

import com.lannisterlogics.UserService.exceptions.ResourceNotFoundException;
import com.lannisterlogics.UserService.model.Address;
import com.lannisterlogics.UserService.model.User;
import com.lannisterlogics.UserService.model.UserRequest;
import com.lannisterlogics.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User createUserWithAddress(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest);
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
        user.setNoOfDirectors(userRequest.getNoOfDirectors());

        Address address = new Address();
        address.setPostalCode(userRequest.getPostalCode());
        address.setHouseNo(userRequest.getHouseNo());
        address.setCountry(userRequest.getCountry());
        address.setYearsOfLiving(userRequest.getYearsOfLiving());

        return userService.saveUserWithAddress(user, address);
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
