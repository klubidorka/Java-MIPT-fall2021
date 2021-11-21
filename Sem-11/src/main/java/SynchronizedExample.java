import java.util.stream.Stream;

public class SynchronizedExample {
    public static void main(String[] args) {
        Increment2 inc2 = new Increment2();
        Stream.iterate(0, n -> n + 1).limit(10_000).parallel().forEach(n -> inc2.increment());
        System.out.println(inc2.value);
    }
}


class Increment2 {
    int value = 0;

    synchronized void increment() {
        value++;
    }
}