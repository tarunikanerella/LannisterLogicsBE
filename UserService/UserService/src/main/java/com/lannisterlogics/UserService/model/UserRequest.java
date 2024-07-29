package com.lannisterlogics.UserService.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private Long businessId;
    private Long platformId;
    private String businessName;
    private String revenue;
    private String founderName;
    private LocalDate DOB;
    private String legalStructure;
    private String contact;
    private String businessEmail;
    private String noOfDirectors;

    private String postalCode;
    private String houseNo;
    private String country;
    private String yearsOfLiving;
}
