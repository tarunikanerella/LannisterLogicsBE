package com.lannisterlogics.LoanService.repository;

import com.lannisterlogics.LoanService.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPayerIdOrPayeeId(Long payerId, Long payeeId);

}