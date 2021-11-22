package task;

import java.util.concurrent.ThreadLocalRandom;

public class Writer implements Runnable {
    private final long WRITER_DELAY = 400; // milliseconds
    private final ToyDB database;
    private int id;

    Writer(ToyDB database, int id) {
        this.database = database;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(WRITER_DELAY));
            database.write(id);
        } catch (InterruptedException ignored) {
        }
    }
}
