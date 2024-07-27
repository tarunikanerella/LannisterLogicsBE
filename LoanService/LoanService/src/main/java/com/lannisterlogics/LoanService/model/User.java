package com.lannisterlogics.LoanService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    //  @SequenceGenerator(name = "userEntitySequence", sequenceName = "seq_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long businessId;

    //needs to be mapped via foreign key to Platform table with platformId - one to one
    private Long platformId;

    private String businessName;

    private String revenue;

    //needs to be mapped via foreign key to Address table with userId - one to one
    private Long addressId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;

    private String founderName;

    private LocalDate DOB;

    private String legalStructure;

    private String contact;

    private String businessEmail;

    private String noOfDirectors;

    private Boolean existingBorrowing;

    private String existingBorrowingAmount;
}
