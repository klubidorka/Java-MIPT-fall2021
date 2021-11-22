package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try {
            runDB(20, 2, new BetterDB());
        } catch (InterruptedException ignored){}
    }

    private static void runDB(int readersAmount, int writersAmount, ToyDB database) throws InterruptedException {
        assert readersAmount > 0;
        assert writersAmount > 0;

        List<Thread> threads = new ArrayList<>(readersAmount + writersAmount);

        for (int i = 0; i < readersAmount; i++){
            threads.add(new Thread(new Reader(database, i)));
        }
        for (int i = readersAmount; i <  readersAmount + writersAmount; i++){
            threads.add(new Thread(new Writer(database, i)));
        }

        Collections.shuffle(threads);

        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads){
            thread.join();
        }
    }
}
