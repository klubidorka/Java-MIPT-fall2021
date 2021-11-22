import java.util.stream.Stream;

public class LowLevelSynchronization {
    public static void main(String[] args) {
        solutionAttemptSynchronized();
        solutionAttemptVolatile();
    }

    private static void solutionAttemptSynchronized() {
        Increment2 inc2 = new Increment2();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc2.increment());
        System.out.println(inc2.value);
    }

    private static void solutionAttemptVolatile() {
        Increment3 inc3 = new Increment3();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc3.increment());
        System.out.println(inc3.value);
    }
}

class Increment2 {

    /**
     * Constructors cannot be synchronized. Using the synchronized keyword with a constructor is a syntax error.
     * Synchronizing constructors doesn't make sense, because only the thread that creates an object
     * should have access to it while it is being constructed.
     */
//    synchronized IncrementSynch(){
//        // do smth
//    }

    int value = 0;

    // will this help?
    synchronized void increment() {
        value++;
    }
}

class Increment3 {
    // will this help?
    volatile int value = 0;

    void increment() {
        value++;
    }
}


class WaitNotify {
    private boolean condition = false;
    private Object obj = new Object();

    void waitForCondition() {
        while (!condition) {
            try {
                obj.wait();
            } catch (InterruptedException ignored) {
            }
        }
    }

    void activateCondition() {
        condition = true;
        obj.notify();
    }
}