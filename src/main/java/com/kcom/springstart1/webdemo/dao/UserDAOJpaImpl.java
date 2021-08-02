package com.kcom.springstart1.webdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.kcom.springstart1.webdemo.model.User;

@Repository
public class UserDAOJpaImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllTransactions() {
        Query theQuery= (Query) entityManager.createQuery("from User");
        List<User> transactions = theQuery.getResultList();
       return transactions;
    }

    @Override
    public User findTransactionById(int theId) {
        User theTransaction = entityManager.find(User.class,theId);
        return theTransaction;
    }

    @Override
    public User saveTransaction(User theTransaction) {
        User dbTransaction = entityManager.merge(theTransaction);
        theTransaction.setId(dbTransaction.getId());
        return theTransaction;
    }

    @Override
    public void deleteTransactionById(int theId) {
        Query theQuery = (Query) entityManager.createQuery("delete from user where id=:transactionId");
        theQuery.setParameter("transactionId", theId);
        theQuery.executeUpdate();
    }
    
}
