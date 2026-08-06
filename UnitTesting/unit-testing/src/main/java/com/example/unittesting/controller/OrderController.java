package com.example.unittesting.controller;


import com.example.unittesting.service.OrderService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("order")
    public void justBefore(){
        System.out.println(orderService);
        orderService.placeOrder("bomb");
    }

    @GetMapping("/risky")
    public void risky(){
        orderService.riskyOrder("Bomb");
    }


}
