package com.lannisterlogics.LoanService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String organisationType;

    private String loansProvided;

    private String contact;

    private String businessEmail;

    private String legalStructure;

    @OneToOne(mappedBy = "platform", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;

}
