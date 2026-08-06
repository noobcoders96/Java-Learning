package com.example.unittesting.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {
    public String placeOrder(String item) {
        System.out.println("Placing order for: " + item);
        return "placed order for: " + item;
    }

    public void riskyOrder(String item) {
        if (item.equalsIgnoreCase("bomb")) {
            throw new RuntimeException("Not allowed!");
        }
        System.out.println("Risky order placed: " + item);
    }
}

