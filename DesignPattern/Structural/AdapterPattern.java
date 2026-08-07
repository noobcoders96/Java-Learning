package DesignPattern.Structural;

import java.util.Random;

public class AdapterPattern {
    static void main(String[] args){

        /**
         * so intially we are using RazorPayment later it becomes depreciated
         * if you see stripePay has different method name for processing money and razorPay has different method name
         * to overcome this incompatibility we create adapter that implements our interface
         * so we would never know that 3rd part api library has changed since we would be still calling .process()
         * we implement new adapter for each 3rd part dependency and inject it
         * we still would be calling   paymentProcessor.process
         * thats why we say adapter pattern resolves issue between two incompatible interfaces
         * */
        //PaymentProcessor paymentProcessor= new RazorPaymentProcessorAdapter();
        //if tmmr stripePay comes into picture just comment above line
        PaymentProcessor paymentProcessor= new StripePaymentProcessorAdapter();
        System.out.println(paymentProcessor.process("2000","12ACt","1234"));


    }
    public static interface PaymentProcessor{
        public boolean process(String amount,String accountNumber,String pin);
    }

    public static class RazorPay{
        //imagine this as 3rd party library or dependency (which becomes deprecated in future)
        public boolean doPayment(String accountNumber,String amount,String pin){
            return new Random().nextBoolean();
        }
    }
    public static class RazorPaymentProcessorAdapter implements PaymentProcessor{

        RazorPay razorPay = new RazorPay();

        public boolean process(String amount,String accountNumber,String pin){
            System.out.println("Razor payment called");
            return razorPay.doPayment(accountNumber,amount,pin);
        }
    }
    public static class StripePay{
        //this is another 3rd party api
        public boolean easyPayment(String accountNumber,String pin,String amount){

            return new Random().nextBoolean();
        }
    }
    public static class StripePaymentProcessorAdapter implements PaymentProcessor{
        StripePay stripePay = new StripePay();
        public boolean process(String amount,String accountNumber,String pin){
            System.out.println("Stripe payment called");
            return stripePay.easyPayment(accountNumber,pin,amount);
        }
    }
}

