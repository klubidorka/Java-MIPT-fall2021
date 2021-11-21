import java.util.stream.Stream;

public class ProblemsWithShareState {
    public static void main(String[] args) throws InterruptedException {
        raceCondition();
        Reordering.main();
    }

    public static void raceCondition() {
        // OK
        Increment inc = new Increment();
        Stream.iterate(0, n -> n + 1).limit(10_000).forEach(n -> inc.increment());
        System.out.println(inc.value);

        // RACE CONDITION
        Increment inc1 = new Increment();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc1.increment());
        System.out.println(inc1.value);
    }

}

class Increment {
    int value = 0;

    void increment() {
        value++;
    }
}

class Reordering {
    static int x = 0;
    static int y = 0;
    static int a = 0;
    static int b = 0;

    public static void main() throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread two = new Thread(() -> {
            b = 1;
            y = a;
        });

        one.start();
        two.start();

        one.join();
        two.join();

        // It's possible to see x = 0, y = 0
        System.out.printf("x = %d, y = %d", x, y);
    }
}