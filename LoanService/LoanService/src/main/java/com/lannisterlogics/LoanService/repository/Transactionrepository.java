package com.lannisterlogics.LoanService.repository;

import com.lannisterlogics.LoanService.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Transactionrepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPayerIdOrPayeeId(Long payerId, Long payeeId);

}
