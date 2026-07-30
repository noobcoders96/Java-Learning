import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadExample {
    public static void main(String[] args) {

        Thread t1=Thread.ofVirtual().start(//No lifecycle maintained
                ()->{
                    System.out.println("Thread "+Thread.currentThread().getName()+" is running");
                }
        );

        try {
            t1.join();
            System.out.println(t1.isAlive());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try(ExecutorService exec =Executors.newVirtualThreadPerTaskExecutor()){
            exec.submit(()->{
                System.out.println("Thread "+Thread.currentThread().getName()+" is running");
            });
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
