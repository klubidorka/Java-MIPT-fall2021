package streams.demo;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperations {
    public static void main(String[] args) {

        // Example of 'map'. Argument of 'map' is a lambda function
        Stream.of("a", "b", "c").map(n -> n + "_1").forEach(System.out::println);

        // Flat map return Stream of elements instead of single element
        Stream.of("a", "b", "c").flatMap(n -> Stream.of(n , n + "_1")).forEach(System.out::println);

        // Use this to convert stream of objects to stream of primitives
        Stream.of("-1", "1", "1234").mapToInt(Integer::parseInt).forEach(System.out::println);

        // Terminal operations that return element of a stream return Optional<T>.
        // This wrapper is used to unify cases of empty and not empty streams
        // (imagine that stream is empty and a function needs to return it's first element. What to return? Null?
        // But returning null is a bad idea, because it may cause nullPointerException. So, it was decided to create
        // this generic wrapper)
        // You can use orElse(T) to avoid Optional<arg>. In this case function will return normal value T or if stream
        // is empty, it will return 'arg'
        Stream.of("-1", "1", "1234").findFirst().orElse("");

        Stream.of("-1", "1", "1234").findAny().orElse("");

        // Converts stream to collection. You can either use standard collector or create your collector if you need
        // to return custom collection
        Stream.of("-1", "1", "1234").collect(Collectors.toList());

        // Returns amount of elements in stream
        Stream.of("-1", "1", "1234").count();

        // true if any element of stream passed to lambda provided return true
        // In this case 'n == 1' is the condition
        Stream.of(-1, 1, 1234).anyMatch(n -> n == 1);

        // min / max functions need a comparator
        Stream.of(-1, 1, 1234).min(Integer::compareTo).orElse(0);

        // Convert stream to array
        Stream.of("-1", "1", "1234").toArray(String[]::new);

        // Performs reduce operation on stream.
        // Function must be associative (https://en.wikipedia.org/wiki/Associative_property)
        //
        // It works step by step
        // (a, b, c, d, ...) -> (f(a, b), c, d) -> (f(f(a, b), c), d, ...) -> (f(f(f(a,b), c), d), ...)
        // in this example ("-1", "1", "1234") -> ("-11", "1234") -> "-111234"
        Stream.of("-1", "1", "1234").reduce((a, b) -> a + b).orElse("");
    }
}
