package com.kcom.springstart1.webdemo.dao;

import java.util.List;

import com.kcom.springstart1.webdemo.model.User;


public interface UserDAO{

    List<User> getAllTransactions();

    User findTransactionById(int theId);

    User saveTransaction(User theTransaction);

    void deleteTransactionById(int theId);

}
