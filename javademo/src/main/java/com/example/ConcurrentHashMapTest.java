package com.example;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

class Counter {
    private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
    public void increase(String key) {
        Integer oldValue,newValue;
        while (true) {
            oldValue = map.get(key);
            if (oldValue == null) {
                newValue = 1;
                if (map.putIfAbsent(key,newValue) == null) {
                    break;
                }
            } else {
                newValue = oldValue + 1;
                if (map.replace(key,oldValue,newValue)) {
                    break;
                }
            }
        }
    }

    public Integer get(String key) {
        return map.get(key);
    }
}
public class ConcurrentHashMapTest {

    public static final int CALLTIME = 1000000;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        final Counter counter = new Counter();
        final CountDownLatch latch = new CountDownLatch(CALLTIME);
        for (int i=0; i < CALLTIME; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    counter.increase("test");
                    latch.countDown();
                }
            });
        }
        latch.await();
        es.shutdown();
        
        es.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(counter.get("test"));
    }
}
