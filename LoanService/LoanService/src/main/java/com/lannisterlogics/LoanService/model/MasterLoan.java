package com.lannisterlogics.LoanService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MasterLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double productMargin;

    @OneToOne(mappedBy = "loantype", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Loan loan;

    private Double platformMargin;

    private String loanName;

    private String loanDescription;

    private String imageLink;
}
