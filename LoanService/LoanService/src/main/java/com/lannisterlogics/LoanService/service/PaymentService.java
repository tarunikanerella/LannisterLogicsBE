package com.lannisterlogics.LoanService.service;

import com.lannisterlogics.LoanService.Constants.DirectionFlagEnum;
import com.lannisterlogics.LoanService.model.Loan;
import com.lannisterlogics.LoanService.model.Transaction;
import com.lannisterlogics.LoanService.repository.LoanRepository;
import com.lannisterlogics.LoanService.repository.Transactionrepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private final Transactionrepository transactionrepository;

    private final LoanRepository loanRepository;

    public PaymentService(Transactionrepository transactionrepository, LoanRepository loanRepository) {
        this.transactionrepository = transactionrepository;
        this.loanRepository = loanRepository;
    }

    public Transaction createTransaction(Long payerId, Long payeeId, Long loanId,
                                         LocalDateTime dateOfTransaction, Double amount,
                                         String status, DirectionFlagEnum transactionType) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        boolean paymentProcessed = simulatePaymentProcessing(amount, dateOfTransaction);


        Transaction transaction = new Transaction();
        transaction.setPayerId(payerId);
        transaction.setPayeeId(payeeId);
        transaction.setLoan(loan);
        transaction.setDateOfTransaction(dateOfTransaction);
        transaction.setAmount(amount);
        transaction.setStatus(status);
        transaction.setDirectionFlag(transactionType);

        return transactionrepository.save(transaction);
    }

    public Transaction getTransactionById(Long id) {
        return transactionrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    private boolean simulatePaymentProcessing(Double amount, LocalDateTime dateOfTransaction) {
        int minute = dateOfTransaction.getMinute();
        return minute % 2 == 0 ? true : false;
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {

        return transactionrepository.findByPayerIdOrPayeeId(userId, userId);

    }
}



