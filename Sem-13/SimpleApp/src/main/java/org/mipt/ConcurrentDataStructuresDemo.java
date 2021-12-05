package org.mipt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentDataStructuresDemo {
    final static int SIZE = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        runSpeedTest();
    }

    static void runSpeedTest() throws InterruptedException {
        System.out.printf("Single thread implementation spent %d ms\n", SingleThreadHashMapSpeedTest());
        System.out.printf("2-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(2));
        System.out.printf("4-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(4));
        System.out.printf("8-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(8));
    }

    static long SingleThreadHashMapSpeedTest() throws InterruptedException {
        long start = System.currentTimeMillis();

        Map<Integer, String> map = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            map.put(i, String.format("Value for int %d", i));
//            Thread.sleep(10);
        }

        return System.currentTimeMillis() - start;
    }

    static long MultiThreadHashMapSpeedTest(int threadsAmount) throws InterruptedException {
        assert threadsAmount > 1;
        long start = System.currentTimeMillis();

        Map<Integer, String> map = new ConcurrentHashMap<>(SIZE);
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);
        List<Callable<Boolean>> tasks = new ArrayList<>(threadsAmount);

        for (int i = 0; i < threadsAmount; i++) {
            int finalI = i;
            tasks.add(() -> {
                int offset = SIZE / threadsAmount;
                for (int k = 0; k < offset; k++) {
                    int key = finalI * offset + k;
                    map.put(key, String.format("Value for int %d", key));
//                    Thread.sleep(10);
                }
                return true;
            });
        }
        executorService.invokeAll(tasks);
        executorService.shutdownNow();
        return System.currentTimeMillis() - start;
    }
}