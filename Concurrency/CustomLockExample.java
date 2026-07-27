import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomLockExample {

    public static void main(String[] args) throws InterruptedException {

        // ==========================================================
        // ReentrantLock using lock()
        // ==========================================================

        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();

        System.out.println("\n================ ReentrantLock using lock() ================\n");

        Thread lockThread1 = new Thread(() -> {
            Thread.currentThread().setName("Lock-Thread-1");
            try {
                reentrantLockExample.showMessageUsingLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread lockThread2 = new Thread(() -> {
            Thread.currentThread().setName("Lock-Thread-2");
            try {
                reentrantLockExample.showMessageUsingLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        lockThread1.start();

        Thread.sleep(1000);

        lockThread2.start();

        lockThread1.join();
        lockThread2.join();

        // ==========================================================
        // ReentrantLock using tryLock()
        // ==========================================================

        System.out.println("\n================ ReentrantLock using tryLock() ================\n");

        Thread tryThread1 = new Thread(() -> {
            Thread.currentThread().setName("TryLock-Thread-1");
            try {
                reentrantLockExample.showMessageUsingTryLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread tryThread2 = new Thread(() -> {
            Thread.currentThread().setName("TryLock-Thread-2");
            try {
                reentrantLockExample.showMessageUsingTryLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tryThread1.start();

        Thread.sleep(1000);

        tryThread2.start();

        tryThread1.join();
        tryThread2.join();

        // ==========================================================
        // ReadWriteLock Demo
        // ==========================================================

        ReadWriteLockExample readWriteLockExample = new ReadWriteLockExample();

        // ==========================================================
        // Scenario-1
        // Reader holds lock -> Writer tries
        // ==========================================================

        System.out.println("\n================ SCENARIO-1 =================");
        System.out.println("Reader holds READ lock -> Writer tries WRITE lock\n");

        Thread reader = new Thread(() -> {
            Thread.currentThread().setName("Reader");
            readWriteLockExample.sharedLockMethod();
        });

        Thread writer = new Thread(() -> {
            Thread.currentThread().setName("Writer");
            readWriteLockExample.exclusiveLockMethodTry();
        });

        reader.start();

        Thread.sleep(1000);

        writer.start();

        reader.join();
        writer.join();

        // ==========================================================
        // Scenario-2
        // Writer holds lock -> Reader tries
        // ==========================================================

        System.out.println("\n================ SCENARIO-2 =================");
        System.out.println("Writer holds WRITE lock -> Reader tries READ lock\n");

        Thread writer2 = new Thread(() -> {
            Thread.currentThread().setName("Writer");
            readWriteLockExample.exclusiveLockMethod();
        });

        Thread reader2 = new Thread(() -> {
            Thread.currentThread().setName("Reader");
            readWriteLockExample.sharedLockMethodTry();
        });

        writer2.start();

        Thread.sleep(1000);

        reader2.start();

        writer2.join();
        reader2.join();
    }

    // ==========================================================
    // ReentrantLock Example
    // ==========================================================

    static class ReentrantLockExample {

        private final ReentrantLock lock = new ReentrantLock();

        // ----------------------------------------------------------
        // Demonstrates lock()
        // Thread waits automatically if lock isn't available.
        // ----------------------------------------------------------

        public void showMessageUsingLock() throws InterruptedException {

            System.out.println(Thread.currentThread().getName()
                    + " trying to acquire lock...");

            lock.lock();

            try {

                System.out.println(Thread.currentThread().getName()
                        + " acquired lock");

                Thread.sleep(5000);

                System.out.println(Thread.currentThread().getName()
                        + " completed work");

            } finally {

                System.out.println(Thread.currentThread().getName()
                        + " released lock");

                lock.unlock();
            }
        }

        // ----------------------------------------------------------
        // Demonstrates tryLock()
        // Thread immediately returns if lock isn't available.
        // ----------------------------------------------------------

        public void showMessageUsingTryLock() throws InterruptedException {

            System.out.println(Thread.currentThread().getName()
                    + " trying to acquire lock...");

            if (lock.tryLock()) {

                try {

                    System.out.println(Thread.currentThread().getName()
                            + " acquired lock");

                    Thread.sleep(5000);

                    System.out.println(Thread.currentThread().getName()
                            + " completed work");

                } finally {

                    System.out.println(Thread.currentThread().getName()
                            + " released lock");

                    lock.unlock();
                }

            } else {

                System.out.println(Thread.currentThread().getName()
                        + " could NOT acquire lock.");

                // If required, retry logic can be implemented here.
            }
        }
    }

    // ==========================================================
    // ReadWriteLock Example
    // ==========================================================

    static class ReadWriteLockExample {

        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        private String str = "Hello World";

        // ----------------------------------------------------------
        // Reader uses lock()
        // ----------------------------------------------------------

        public void sharedLockMethod() {

            lock.readLock().lock();

            try {

                System.out.println(Thread.currentThread().getName()
                        + " acquired READ lock");

                Thread.sleep(5000);

                System.out.println(Thread.currentThread().getName()
                        + " Reading : " + str);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                System.out.println(Thread.currentThread().getName()
                        + " released READ lock");

                lock.readLock().unlock();
            }
        }

        // ----------------------------------------------------------
        // Reader uses tryLock()
        // ----------------------------------------------------------

        public void sharedLockMethodTry() {

            if (lock.readLock().tryLock()) {

                try {

                    System.out.println(Thread.currentThread().getName()
                            + " acquired READ lock");

                    System.out.println(Thread.currentThread().getName()
                            + " Reading : " + str);

                } finally {

                    System.out.println(Thread.currentThread().getName()
                            + " released READ lock");

                    lock.readLock().unlock();
                }

            } else {

                System.out.println(Thread.currentThread().getName()
                        + " could NOT acquire READ lock");
            }
        }

        // ----------------------------------------------------------
        // Writer uses lock()
        // ----------------------------------------------------------

        public void exclusiveLockMethod() {

            lock.writeLock().lock();

            try {

                System.out.println(Thread.currentThread().getName()
                        + " acquired WRITE lock");

                Thread.sleep(5000);

                str = "Updated By " + Thread.currentThread().getName();

                System.out.println(Thread.currentThread().getName()
                        + " Updated String");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                System.out.println(Thread.currentThread().getName()
                        + " released WRITE lock");

                lock.writeLock().unlock();
            }
        }

        // ----------------------------------------------------------
        // Writer uses tryLock()
        // ----------------------------------------------------------

        public void exclusiveLockMethodTry() {

            if (lock.writeLock().tryLock()) {

                try {

                    System.out.println(Thread.currentThread().getName()
                            + " acquired WRITE lock");

                    Thread.sleep(3000);

                    str = "Updated By " + Thread.currentThread().getName();

                    System.out.println(Thread.currentThread().getName()
                            + " Updated String");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    System.out.println(Thread.currentThread().getName()
                            + " released WRITE lock");

                    lock.writeLock().unlock();
                }

            } else {

                System.out.println(Thread.currentThread().getName()
                        + " could NOT acquire WRITE lock");
            }
        }
    }
}