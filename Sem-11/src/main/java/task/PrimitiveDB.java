package task;

import java.util.concurrent.ThreadLocalRandom;

public class PrimitiveDB implements ToyDB {
    private long READ_TIME = 500; // milliseconds
    private long WRITE_TIME = 1000; // milliseconds

    @Override
    public synchronized void read(int readerID) {
        System.out.printf("Reader %d started reading\n", readerID);
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(READ_TIME));
        } catch (InterruptedException ignored) {
        }
        System.out.printf("Reader %d finished reading\n", readerID);
    }

    @Override
    public synchronized void write(int writerId) {
        System.out.printf("Writer %d started writing\n", writerId);
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(WRITE_TIME));
        } catch (InterruptedException ignored) {
        }
        System.out.printf("Writer %d finished writing\n", writerId);
    }
}
