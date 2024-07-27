package com.lannisterlogics.LoanService.repository;

import com.lannisterlogics.LoanService.model.MasterLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterLoanRepository extends JpaRepository<MasterLoan, Long> {
}
