package com.lannisterlogics.LoanService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan {


 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 private String loanType;

 private Integer termYear;

 private Long initialPrincipal;

 private Long outstandingPrincipal;

 private Integer interest;

 private Long interestAmount;

 private Integer outstandingTerm;

 private LocalDateTime dateOfApplication;

 @ManyToOne
 @JoinColumn(name = "user_id", nullable = false)
 private User user;

 private Long platform;

 @JsonIgnore
 @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private List<Transaction> transaction;

 private Boolean existingBorrowing;

 private String existingBorrowingAmount;

 private String status;

 private String netProfit;

 private String businessCreditScore;

}
