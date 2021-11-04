package streams.tasks;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


/**
 * This class contains tasks that should be solved using stream API only.
 * Check your solutions with TaskTester (in src/main/test/java/ folder)
 */
public class Tasks<T> {

    /**
     * Return amount of elements equal to pattern
     * For example, task1(Stream.of("a", "a", "b"), "a") = 2
     */
    public long task1(Stream<T> stream, T pattern) {
        // YOUR CODE HERE
        return 0;
    }

    /**
     * Return last element of collection or null if collection is empty
     */
    public T task2(Collection<T> collection) {
        // YOUR CODE HERE
        return null;
    }

    /**
     * Return list of two elements starting from the second.
     * It is guaranteed that size of the stream is greater than two
     */
    public List<T> task3(Stream<T> stream) {
        // YOUR CODE HERE
        return null;
    }

    /**
     * Return true if at least one element equal to "pattern" can be found in stream, else return false
     */
    public boolean task4(Stream<T> stream, T pattern) {
        // YOUR CODE HERE
        return false;
    }

    /**
     * Return sorted list of stream elements without duplicates
     */
    public List<T> task5(Stream<T> stream) {
        // YOUR CODE HERE
        return null;
    }
}
