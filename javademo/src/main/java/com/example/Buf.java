package com.example;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Buf {
    private final int MAX = 5;
    private final ArrayList<Integer> list = new ArrayList<Integer>();
    synchronized void put(int v) throws InterruptedException {
        if (list.size() == MAX) {
            wait();
        }
        list.add(v);
        notifyAll();
    }

    synchronized int get() throws InterruptedException {
        while (list.size() == 0) {
            wait();
        }
        int value = list.remove(0);
        notifyAll();
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        final Buf buf = new Buf();
        ExecutorService es = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 1; i++) {
            es.execute(new Runnable() {
                long lastTime = 0;
                @Override
                public void run() {
                    while (true) {
                        try {
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - lastTime > 10 * 1000) {
                                System.out.println("ThreadName:-->" + Thread.currentThread().getName());
                                lastTime = currentTime;
                            }
                            buf.put(1);
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {
                long lastTime = 0;
                @Override
                public void run() {
                    while (true) {
                        try {
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - lastTime > 10 * 1000) {
                                System.out.println("ThreadName:-->" + Thread.currentThread().getName());
                                lastTime = currentTime;
                            }

                            buf.get();
                            Thread.sleep(10);

                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });

        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.DAYS);
    }
}
