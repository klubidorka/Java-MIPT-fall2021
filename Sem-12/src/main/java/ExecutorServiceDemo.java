import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            // Calculate something super important
            TimeUnit.SECONDS.sleep(5);
            return 3;
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(task);

        tryToGetResultImmediately(future);
        tryToGetResultWithDelay(future, 3);
        executor.shutdownNow();
    }

    static void tryToGetResultImmediately(Future<Integer> future) throws ExecutionException, InterruptedException {
        if (future.isDone()) {
            System.out.println("It's ready");
            System.out.printf("The result is %d", future.get());
        } else {
            System.out.println("It's not ready yet");
        }
    }

    static void tryToGetResultWithDelay(Future<Integer> future, int delay) {
        int result;
        try {
            result = future.get(delay, TimeUnit.SECONDS);
            System.out.println("It's ready");
            System.out.printf("The result is %d", result);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("It's not ready yet");
        }
    }
}