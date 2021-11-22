import java.util.Random;

public class ParallelApp {
    private static int[] prepareRandomArray() {
        int N = 100_000_000;
        int[] array = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(10) - 5;
        }
        return array;
    }

    private static void singleThreadCalculations(int[] array) {
        int result = 0;

        long start = System.currentTimeMillis();
        for (int value : array) {
            result += value;
        }
        long execTime = System.currentTimeMillis() - start;

        System.out.printf("Single thread. Sum = %d, execution time: %d ms.\n", result, execTime);
    }

    private static void multiThreadCalculations(int threads, int[] array) throws InterruptedException {
        assert threads > 1;
        long start = System.currentTimeMillis();

        int sectorLength = array.length / threads;
        int sum = 0;
        Worker[] workers = new Worker[threads];

        for (int i = 0; i < threads - 1; i++) {
            workers[i] = new Worker(array, i * sectorLength, (i + 1) * sectorLength);
        }
        workers[threads - 1] = new Worker(array, (threads - 1) * sectorLength, array.length);

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
        int[] array = prepareRandomArray();
        singleThreadCalculations(array);
        try {
            multiThreadCalculations(8, array);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker extends Thread {
    private final int[] array;
    private int partialSum = 0;
    private final int from;
    private final int to;

    Worker(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }

    public void run() {
        for (int i = from; i < to; i++) {
            partialSum += array[i];
        }
    }

    int getPartialSum() {
        return partialSum;
    }
}