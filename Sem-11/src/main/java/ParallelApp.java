import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelApp {
    private static List<Integer> prepareRandomArray() {
        int N = 100_000_000;
        List<Integer> list = new ArrayList<>(N);
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            list.add(random.nextInt(10) - 5);
        }
        return list;
    }

    private static void singleThreadCalculations(List<Integer> list) {
        long start = System.currentTimeMillis();
        int result = list.stream().mapToInt(x -> x).sum();
        long execTime = System.currentTimeMillis() - start;
        System.out.printf("Single thread. Sum = %d, execution time: %d ms.\n", result, execTime);
    }

    private static void multiThreadCalculations(int threads, List<Integer> list) throws InterruptedException {
        assert threads > 1;
        long start = System.currentTimeMillis();

        int sectorLength = list.size() / threads;
        int sum = 0;
        List<Worker> workers = new ArrayList<>(threads);

        for (int i = 0; i < threads - 1; i++) {
            workers.add(new Worker(list, i * sectorLength, (i + 1) * sectorLength));
        }
        workers.add(new Worker(list, (threads - 1) * sectorLength, list.size()));

        for (Worker worker : workers) {
            worker.start();
        }

        for (Worker worker : workers) {
            worker.join();
            sum += worker.getPartialSum();
        }
        long execTime = System.currentTimeMillis() - start;
        System.out.printf("Used %d threads. Sum = %d, execution time: %d ms.\n", threads, sum, execTime);
    }

    public static void main(String[] args) {
        List<Integer> list = prepareRandomArray();
        singleThreadCalculations(list);
        try {
            multiThreadCalculations(4, list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker extends Thread {
    private final List<Integer> list;
    private int partialSum = 0;
    private final int from;
    private final int to;

    Worker(List<Integer> list, int from, int to) {
        this.list = list;
        this.from = from;
        this.to = to;
    }

    public void run() {
        for (int i = from; i < to; i++) {
            partialSum += list.get(i);
        }
    }

    int getPartialSum() {
        return partialSum;
    }
}