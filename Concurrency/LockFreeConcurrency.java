import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeConcurrency {
    public static void main(String[] args) {

        Demo d=new Demo();
        for(int i=0;i<10000;i++){
            Thread t1=new Thread(()->{
                d.id++;
            });
            t1.start();
        }

        AtomicInteger b = new AtomicInteger(0);
        for(int i=0;i<10000;i++){
            b.incrementAndGet();
        }

        System.out.println(d.id);//choatic operation
        System.out.println(b);//CAS Operation applied so no operation loss will happen
    }
    public static class Demo{
        int id=0;
    }
}
