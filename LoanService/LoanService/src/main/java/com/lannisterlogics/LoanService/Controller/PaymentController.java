package com.lannisterlogics.LoanService.Controller;

import com.lannisterlogics.LoanService.Constants.DirectionFlagEnum;
import com.lannisterlogics.LoanService.model.Transaction;
import com.lannisterlogics.LoanService.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestParam Long payerId,
                                         @RequestParam Long payeeId,
                                         @RequestParam Long loanId,
                                         @RequestParam LocalDateTime dateOfTransaction,
                                         @RequestParam Double amount,
                                         @RequestParam String status,
                                         @RequestParam DirectionFlagEnum transactionType) {
        return paymentService.createTransaction(payerId, payeeId, loanId, dateOfTransaction, amount, status, transactionType);
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return paymentService.getTransactionById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return paymentService.getTransactionsByUserId(userId);
    }

    @GetMapping("/{loadId}")
    public Double getAmount(@PathVariable Long loanId) {

        return paymentService.getInstallmentAmount(loanId);
    }

}
