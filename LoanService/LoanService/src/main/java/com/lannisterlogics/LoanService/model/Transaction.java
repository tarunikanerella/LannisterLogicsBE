package com.lannisterlogics.LoanService.model;

import com.lannisterlogics.LoanService.Constants.DirectionFlagEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    //  @SequenceGenerator(name = "userEntitySequence", sequenceName = "seq_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //needs to be mapped via foreign key to User table with userId - many to one - discuss
    private Long payerId;

    //needs to be mapped via foreign key to User table with userId - many to one - discuss
    private Long payeeId;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    private LocalDate dateOfTransaction;

    private Long amount;

    private String status;

    private DirectionFlagEnum directionFlag;
}
