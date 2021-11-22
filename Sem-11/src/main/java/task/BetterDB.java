package task;

import java.util.concurrent.ThreadLocalRandom;

public class BetterDB implements DB {
    long READ_TIME = 500; // milliseconds
    long WRITE_TIME = 1000; // milliseconds

    int readers = 0;

    @Override
    public void read(int readerID) {
        synchronized (this) {
            readers++;
            System.out.printf("Reader %d started reading\n", readerID);
        }

        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(READ_TIME));
        } catch (InterruptedException ignored) {
        }

        synchronized (this) {
            readers--;
            System.out.printf("Reader %d finished reading\n", readerID);
            if (readers == 0) {
                this.notifyAll();
            }
        }
    }

    @Override
    public synchronized void write(int writerId) {
        try {
            while (readers > 0) {
                this.wait();
            }
        } catch (InterruptedException ignored) {
        }

        System.out.printf("Writer %d started writing\n", writerId);
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(random.nextLong(WRITE_TIME));
        } catch (InterruptedException ignored) {
        }

        System.out.printf("Writer %d finished writing\n", writerId);
    }
}
