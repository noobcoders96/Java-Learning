package com.example.unittesting.service;

import com.example.unittesting.entity.Account;
import com.example.unittesting.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void detectBalance(String accNumber,double detect) {
        System.out.println("Entered Transactional Method "+Thread.currentThread().getName());
        Account acc=accountRepository.findByAccountNumber(accNumber).orElseThrow(()->{
            throw new RuntimeException("Account not found "+accNumber);
        });
        System.out.println("Account Balance : "+acc.getBalance()+ " captured by "+Thread.currentThread().getName());

        if(acc.getBalance()<detect){
            throw new RuntimeException("Balance not enough");
        }
        acc.setBalance(acc.getBalance()-detect);
        System.out.println("Account Balance : "+acc.getBalance()+ " modified by "+Thread.currentThread().getName());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
