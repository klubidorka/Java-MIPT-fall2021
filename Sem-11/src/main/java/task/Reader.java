package task;

import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    private final long READER_DELAY = 500; // milliseconds
    private final ToyDB database;
    private int id;

    Reader(ToyDB database, int id) {
        this.database = database;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(READER_DELAY));
            database.read(id);
        } catch (InterruptedException ignored) {
        }
    }
}
