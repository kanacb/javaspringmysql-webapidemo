package com.kcom.springstart1.webdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.kcom.springstart1.webdemo.dao.UserDAO;
import com.kcom.springstart1.webdemo.model.User;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class UserServiceImpl implements UserService {
    UserDAO transactionDAO;
    private static final String[] blackListEmails = new String[] { "blacklist1@gmail.com", "blacklist2@gmail.com",
            "blacklist3@gmail.com", "blacklist4@gmail.com" };

    @Autowired
    public UserServiceImpl(@Qualifier("userDAOJpaImpl") UserDAO theTransactionDao) {
        transactionDAO = theTransactionDao;
    }

    @Override
    @Transactional
    public List<User> findAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    @Override
    @Transactional
    public String screenTransactionById(int theId) {
        User theTransaction = transactionDAO.findTransactionById(theId);
        String dateInString = theTransaction.getDate();
        LocalDate localDate = LocalDate.parse(dateInString);
        LocalDate today = LocalDate.now();

        long difference = DAYS.between(localDate, today);
        boolean isInBlackList = Arrays.asList(blackListEmails).contains(theTransaction.getEmail());

        // return Reject if the email id is in blacklist and the transaction has been
        // made in the last 30 days, otherwise return accept
        if (isInBlackList && difference < 30) {
            return "REJECT";
        } else {
            return "ACCEPT";
        }

    }

    @Override
    @Transactional
    public User findTransactionById(int theId) {
        return transactionDAO.findTransactionById(theId);
    }

    @Override
    @Transactional
    public User saveTransaction(User theTransaction) {
        return transactionDAO.saveTransaction(theTransaction);
    }

    @Override
    @Transactional
    public int deleteTransactionById(int theId) {
        transactionDAO.deleteTransactionById(theId);
        return theId;
    }

}
