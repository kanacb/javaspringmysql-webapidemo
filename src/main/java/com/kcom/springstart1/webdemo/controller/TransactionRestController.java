package com.kcom.springstart1.webdemo.controller;

import java.util.List;

import com.kcom.springstart1.webdemo.model.User;
import com.kcom.springstart1.webdemo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/user")
public class TransactionRestController {

    private UserService transactionService;

    @Autowired
    public TransactionRestController(UserService thetransactionservice) {
        transactionService = thetransactionservice;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAll() {
        System.out.println(transactionService.findAllTransactions().size());
        return new ResponseEntity<List<User>>(transactionService.findAllTransactions(), HttpStatus.OK);
    }

    @RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.GET)
    public String screenTransaction(@PathVariable int transactionId) {
        String theTransaction = transactionService.screenTransactionById(transactionId);
        return theTransaction;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public User addTransaction(@RequestBody User theTransaction) {
        return (transactionService.saveTransaction(theTransaction));
    }

    // For updating a transaction
    @RequestMapping(value = "/transactions", method = RequestMethod.PUT)
    public User updateTransaction(@RequestBody User theTransaction) {
        User transaction = transactionService.findTransactionById(theTransaction.getId());
        if (transaction == null) {
            throw new RuntimeException("Transaction to update doesn't exist");
        }
        return (transactionService.saveTransaction(theTransaction));
    }

    // For deleting a transaction
    @RequestMapping(value = "/transactions/{transactionId}", method = RequestMethod.DELETE)
    public String deleteTransaction(@PathVariable int transactionId) {
        User tempTransaction = transactionService.findTransactionById(transactionId);
        if (tempTransaction == null) {
            throw new RuntimeException("Transaction Id not found");
        }
        transactionService.deleteTransactionById(transactionId);
        return "deleted transaction id " + transactionId;

    }
}
