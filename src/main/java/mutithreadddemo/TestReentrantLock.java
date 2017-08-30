package main.java.mutithreadddemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("interrupted!!");
                }
            }
        });

        thread1.start();
        thread1.interrupt();
        Thread.sleep(1);
    }
}
