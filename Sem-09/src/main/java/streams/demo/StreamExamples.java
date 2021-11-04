package streams.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {
        speedTest();
    }

    /**
     * Our goal is to find all the products that are cheaper than 14 standard units, sort them by id and print them.
     * This method solves the problem without streams
     */
    public static void beforeJava8Approach() {
        List<Product> products = prepareProducts();

        // Find all the products that are cheaper than 14 standard units
        List<Product> cheapProducts = new ArrayList<>();
        for (Product elem : products) {
            if (elem.getPrice() < 14) {
                cheapProducts.add(elem);
            }
        }

        // Sort them
        Collections.sort(cheapProducts);

        // Print them
        for (Product elem : cheapProducts) {
            System.out.println(elem);
        }
    }

    /**
     * This method solves the problem using stream
     */
    public static void streamApproach() {
        List<Product> products = prepareProducts();

        Stream<Product> prod = products.stream();

        prod.filter(s -> s.getPrice() < 14)
                .limit(3)
                .sorted()
                .forEach(System.out::println);

        // Stream cannot be used more than one time!
        // prod.count(); throws exception
    }


    private static List<Product> prepareProducts() {
        List<Product> products = new ArrayList<>(5);
        products.add(new Product(1, 20, "Banana"));
        products.add(new Product(2, 10, "Candy"));
        products.add(new Product(3, 12, "Juice"));
        products.add(new Product(4, 15, "Apple"));

        return products;
    }

    public static void speedTest() {
        long startTime;
        long notoptTime;
        long optTime;
        long bestTime;

        int streamSize = 1_000_000_000;

        Stream<Integer> stream = Stream.iterate(0, n -> n + 1).limit(streamSize);

        // not optimal solution
        startTime = System.currentTimeMillis();
        boolean result = slow(stream);
        notoptTime = System.currentTimeMillis() - startTime;

        // reload stream
        stream = Stream.iterate(0, n -> n + 1).limit(streamSize);

        // optimal solution
        startTime = System.currentTimeMillis();
        boolean result1 = optimized(stream);
        optTime = System.currentTimeMillis() - startTime;

        // reload stream
        stream = Stream.iterate(0, n -> n + 1).limit(streamSize);

        // best solution
        startTime = System.currentTimeMillis();
        boolean result2 = best(stream);
        bestTime = System.currentTimeMillis() - startTime;

        System.out.printf("Not optimal solution time: %d ms\n" +
                        "Optimal solution time: %d ms\n" +
                        "Best solution time: %d ms\n",
                notoptTime, optTime, bestTime);

        // Not optimal solution time: 9024 ms
        // Optimal solution time: 1 ms
        // Best solution time: 1 ms

        // So, it's important to understand how these methods work!
    }


    static boolean slow(Stream<Integer> stream) {
        return stream.filter(el -> el == 100).count() == 0;
    }

    static boolean optimized(Stream<Integer> stream) {
        return stream.filter(el -> el == 100).limit(1).count() == 0;
    }

    static boolean best(Stream<Integer> stream) {
        return stream.noneMatch(el -> el == 100);
    }
}
