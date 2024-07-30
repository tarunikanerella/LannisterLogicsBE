package com.lannisterlogics.LoanService.service;

import com.lannisterlogics.LoanService.model.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> getAllLoans();

    Loan createLoan(Loan support);

    Loan getLoanById(Long id);

    List<Loan> getLoanByCustId(Long custId);

    Loan updateLoanStatus(Long id, String status);

    List<Loan> getLoanByStatus(String status);

    Loan updateDueAmount(Long id, Long dueAmount);
}
