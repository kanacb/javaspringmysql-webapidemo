package com.kcom.springstart1.webdemo.services;

import java.util.List;

import com.kcom.springstart1.webdemo.model.User;

public interface UserService {

    public List<User> findAllTransactions();

    public String screenTransactionById(int theId);

    public User findTransactionById(int theId);

    public User saveTransaction(User theTransaction);

    public int deleteTransactionById(int theId);

}
