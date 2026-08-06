package com.example.unittesting.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    @Before("execution(* com.example.unittesting.service.OrderService.*(..))")
    public void beforeLogger(JoinPoint jp){
        System.out.println("[Before] method==>"+jp.getSignature());
    }

    @After("execution(* com.example.unittesting.service.OrderService.*(..))")
    public void afterLogger(JoinPoint jp){
        System.out.println("[After] method==>"+jp.getSignature());
    }

    @AfterReturning(
            pointcut = "execution(* com.example.unittesting.service.OrderService.*(..))",
            returning = "result"
    )
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("[AfterReturning] " + jp.getSignature());
        System.out.println("Returned: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.unittesting.service.OrderService.*(..))",
            throwing = "ex"
    )
    public void afterThrowing(JoinPoint jp, Exception ex) {
        System.out.println("[AfterThrowing] " + jp.getSignature());
        System.out.println("Exception: " + ex.getMessage());
    }

    @Around("execution(* com.example.unittesting.service.OrderService.*(..))")
    public void aroundLogger(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("[Around] method==>"+pjp.getSignature());
        pjp.proceed();
        System.out.println("[Around] method Completed==>"+pjp.getSignature());
    }


}
