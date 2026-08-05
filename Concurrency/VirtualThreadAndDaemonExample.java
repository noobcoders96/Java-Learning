import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadAndDaemonExample {
    public static void main(String[] args) {

        Thread t1=Thread.ofVirtual().start(//Manually creating threads
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
            //delegating the job of creating the thread to executor Service so we need to only submit the tasks
            exec.submit(()->{
                System.out.println("Thread "+Thread.currentThread().getName()+" is running");
            });
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        /// Once Main thread finishes tasks  and JVM exists automatically daemon Thread is also terminated
        Thread daemonThread = new Thread(() -> {
            while (true) {
                System.out.println("Daemon is running...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        daemonThread.setDaemon(true);  // make it daemon
        daemonThread.start();

        System.out.println("Main thread running...");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished");


        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            System.out.println(i+" is running ,outside the executor "+Thread.currentThread().getName());
            int a=i;
            executor.submit(()->{
                try {
                    Thread.sleep(5000);
                    System.out.println(a+" is running ,inside the executor "+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        System.out.println("----------------");
        executor.shutdown();// this make sures that exector will stop accepting the tasks and once accepted tasks are completed executor  service will shutdown
        //executor.shutdownNow();//this will shutdown executorService shutdown immediately
    }
}
