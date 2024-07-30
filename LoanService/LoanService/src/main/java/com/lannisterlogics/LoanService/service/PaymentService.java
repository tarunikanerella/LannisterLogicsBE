package com.lannisterlogics.LoanService.service;

import com.lannisterlogics.LoanService.Constants.DirectionFlagEnum;
import com.lannisterlogics.LoanService.model.Loan;
import com.lannisterlogics.LoanService.model.Transaction;
import com.lannisterlogics.LoanService.repository.LoanRepository;
import com.lannisterlogics.LoanService.repository.Transactionrepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Double getInstallmentAmount(Long loanId) {
       Optional<Loan> loan =  loanRepository.findById(loanId);
       Long interestAmount = loan.get().getInterestAmount();
       if(interestAmount==null)
       {
           interestAmount = 0L;
       }
       Long outstandingPrincipal = loan.get().getOutstandingPrincipal();
       Long initialPrincipal = loan.get().getInitialPrincipal();
      Integer term =   loan.get().getTermYear();
      Integer annualInterestRate           = loan.get().getInterest(); // interestRate

       // do the calculation for installment amount

        // outstanding principal = OutstandingPrincipal - (intialPrincipal)/InitialTerm;

        double monthlyInterestRate = annualInterestRate / 12 / 100;
        double amountToPay = (outstandingPrincipal * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -term));
        interestAmount = (long) (interestAmount + amountToPay);

        Long finalInterestAmount = interestAmount;
        Long outstandingPrincipalAmt = outstandingPrincipal -( initialPrincipal/term);
        loan.ifPresent(loan1 -> loan1.setInterestAmount(finalInterestAmount));
        loan.ifPresent(loan1 -> loan1.setOutstandingPrincipal(outstandingPrincipalAmt));
        return amountToPay;
    }
}



