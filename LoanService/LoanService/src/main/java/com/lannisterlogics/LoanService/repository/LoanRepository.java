package com.lannisterlogics.LoanService.repository;

import com.lannisterlogics.LoanService.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
