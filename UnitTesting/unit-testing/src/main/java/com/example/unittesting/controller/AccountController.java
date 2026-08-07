package com.example.unittesting.controller;

import com.example.unittesting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/fire-two-threads")
    public void fireTwoThreads(){
        Thread thread = new Thread(()->{
            Thread.currentThread().setName("Husband");
            accountService.detectBalance("ACC1005",40000);
        });
        Thread thread1 = new Thread(()->{
            Thread.currentThread().setName("Wife");
            accountService.detectBalance("ACC1005",30000);
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread1.start();
    }
}
