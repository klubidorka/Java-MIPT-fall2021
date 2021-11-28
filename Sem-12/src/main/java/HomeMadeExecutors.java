import java.util.concurrent.Executor;

public class HomeMadeExecutors {
}

/**
 * 1) Корректные ли это исполнители?
 * 2) В чем проблема такой реализации?
 */
class DirectExecutor implements Executor {
    public void execute(Runnable runnable) {
        runnable.run();
    }
}

class ThreadPerTaskExecutor implements Executor {
    public void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}