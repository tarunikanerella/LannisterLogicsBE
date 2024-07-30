package com.lannisterlogics.LoanService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "loantype_id", nullable = false)
    private MasterLoan loanType;

    private Integer termYear;

    private Long initialPrincipal;

    private Long outstandingPrincipal;

    private Integer interest;

    private Long interestAmount;

    private LocalDate dateOfApplication;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @JsonIgnore
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Transaction transaction;

    private Boolean existingBorrowing;

    private String existingBorrowingAmount;

}
