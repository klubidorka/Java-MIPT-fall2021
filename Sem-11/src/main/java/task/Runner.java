package task;

public class Runner {
    public static void main(String[] args) {
        try {
            runDB(20, 2, new BetterDB());
        } catch (InterruptedException ignored){}
    }

    private static void runDB(int readersAmount, int writersAmount, DB database) throws InterruptedException {
        assert readersAmount > 0;
        assert writersAmount > 0;

        Thread[] threads = new Thread[readersAmount + writersAmount];

        for (int i = 0; i < readersAmount; i++){
            threads[i] = new Thread(new Reader(database, i));
        }
        for (int i = readersAmount; i <  readersAmount + writersAmount; i++){
            threads[i] = new Thread(new Writer(database, i));
        }

        for (Thread thread : threads){
            thread.start();
        }
        for (Thread thread : threads){
            thread.join();
        }
    }
}
