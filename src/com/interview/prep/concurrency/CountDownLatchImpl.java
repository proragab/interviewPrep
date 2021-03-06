package com.interview.prep.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Nikitash Pawar on 6/11/2017.
 */
public class CountDownLatchImpl {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
 
        Waiter      waiter      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter)     .start();
        new Thread(decrementer).start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Decrementer implements Runnable {

        CountDownLatch latch = null;

        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {

            try {
                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static class Waiter implements Runnable{

        CountDownLatch latch = null;

        public Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter Released");
        }
    }
}
