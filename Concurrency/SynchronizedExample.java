import java.util.*;
public class SynchronizedExample{
public static void main(String[]args){
    try{
    BankAccount jointAccountNonSync=new BankAccount();
    BankAccount jointAccountSync=new BankAccount();

    Thread husbandRequestNonSync=new Thread(()->{
        Thread.currentThread().setName("husbandRequestNonSyncThread");
        try {
            jointAccountNonSync.updateBalanceNonSynchronized(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });

    Thread wifeRequestNonSync=new Thread(()->{
        Thread.currentThread().setName("wifeRequestNonSyncThread");
        try {
            jointAccountNonSync.updateBalanceNonSynchronized(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });

    husbandRequestNonSync.start();
    wifeRequestNonSync.start();

    husbandRequestNonSync.join();
    wifeRequestNonSync.join();

     Thread husbandRequestSync=new Thread(()->{
        Thread.currentThread().setName("husbandRequestSyncThread");
        try {
            jointAccountSync.updateBalance(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });

    Thread wifeRequestSync=new Thread(()->{
        Thread.currentThread().setName("wifeRequestSyncThread");
        try {
            jointAccountSync.updateBalance(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });
    husbandRequestSync.start();
    wifeRequestSync.start();
    husbandRequestSync.join();
    wifeRequestSync.join();

    // Sample output:
/**Inside Non-synchronized method : husbandRequestNonSyncThread
Inside Non-synchronized method : wifeRequestNonSyncThread
Amount deducted : 1000.0 by husbandRequestNonSyncThread
Amount deducted : 1000.0 by wifeRequestNonSyncThread
Inside synchronized method : husbandRequestSyncThread
Amount deducted : 1000.0 by husbandRequestSyncThread
Inside synchronized method : wifeRequestSyncThread
Not enough balance by wifeRequestSyncThread
**/
}
catch(Exception e){
    System.err.println("ERROR : "+e.getLocalizedMessage());
}
}
    public static class BankAccount{
        private double balance=1000;
        private int AccountId=1;


        
        public double getBalance(){
            return balance;
        }
        public synchronized void updateBalance(double amountRequired) throws InterruptedException{
            System.out.println("Inside synchronized method : "+Thread.currentThread().getName());
            
            if(this.balance>=amountRequired){
                Thread.sleep(2000);
                this.balance-=amountRequired;
                System.out.println("Amount deducted : "+amountRequired +" by "+Thread.currentThread().getName());
            }
            else{
                System.out.println("Not enough balance"+" by "+Thread.currentThread().getName());
            }

        }
        public void updateBalanceNonSynchronized(double amountRequired) throws InterruptedException{
            System.out.println("Inside Non-synchronized method : "+Thread.currentThread().getName());
            
            if(this.balance>=amountRequired){
                Thread.sleep(2000);
                this.balance-=amountRequired;
                System.out.println("Amount deducted : "+amountRequired +" by "+Thread.currentThread().getName());
            }
            else{
                System.out.println("Not enough balance"+" by "+Thread.currentThread().getName());
            }

        }
    }

}