import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class AtomicDemo {
    public static void main(String[] args) {
        raceCondition();
        noRaceCondition();
    }

    public static void raceCondition() {
        // RACE CONDITION
        Increment inc1 = new Increment();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc1.increment());
        System.out.println(inc1.value());
    }

    public static void noRaceCondition() {
        // RACE CONDITION
        AtomicIncrement inc2 = new AtomicIncrement();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc2.increment());
        System.out.println(inc2.value());
    }
}

class Increment {
    private int value = 0;

    public void increment() {
        value++;
    }

    public int value() {
        return value;
    }
}

// Original code
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/essential/concurrency/examples/AtomicCounter.java
class AtomicIncrement {
    private final AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }

}