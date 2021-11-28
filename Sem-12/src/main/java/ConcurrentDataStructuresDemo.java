import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentDataStructuresDemo {
    final static int SIZE = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        runSpeedTest();
//        runSpeedTestInverseOrder();
    }

    static void runSpeedTest() throws InterruptedException {
        System.out.printf("Single thread implementation spent %d ms\n", SingleThreadHashMapSpeedTest());
        System.out.printf("2-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(2));
        System.out.printf("4-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(4));
        System.out.printf("8-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(8));
    }

    static void runSpeedTestInverseOrder() throws InterruptedException {
        System.out.printf("8-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(8));
        System.out.printf("4-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(4));
        System.out.printf("2-thread implementation spent %d ms\n", MultiThreadHashMapSpeedTest(2));
        System.out.printf("Single thread implementation spent %d ms\n", SingleThreadHashMapSpeedTest());
    }

    static long SingleThreadHashMapSpeedTest() {
        long start = System.currentTimeMillis();

        Map<Integer, String> map = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            map.put(i, String.format("Value for int %d", i));
        }

        return System.currentTimeMillis() - start;
    }

    static long MultiThreadHashMapSpeedTest(int threadsAmount) throws InterruptedException {
        assert threadsAmount > 1;
        long start = System.currentTimeMillis();

        Map<Integer, String> map = new ConcurrentHashMap<>(SIZE);
        Thread[] threads = new Thread[threadsAmount];

        for (int i = 0; i < threadsAmount; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                int offset = SIZE / threadsAmount;
                for (int k = 0; k < offset; k++) {
                    int key = finalI * offset + k;
                    map.put(key, String.format("Value for int %d", key));
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        return System.currentTimeMillis() - start;
    }
}