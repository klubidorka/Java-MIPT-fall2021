import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
    static class Foo {
        @Override
        public String toString() {
            return "Foo";
        }
    }

    static class Bar extends Foo {
        @Override
        public String toString() {
            return "Bar";
        }
    }

    static void printOverride(Foo foo) {
        System.out.println(foo);
    }

    static void testOverride() {
        // Object <- Foo <- Bar
        printOverride(new Foo());
        printOverride(new Bar());
    }

    ////////////////////////////////////////////////////////////////////////////////

    static void removeAndPrint(Collection<Integer> collection) {
        collection.remove(2);
        System.out.println(collection);
    }

    static void removeAndPrint(List<Integer> list) {
        list.remove(2);
        System.out.println(list);
    }

    static void testCollections() {
        Collection<Integer> collection = Stream.of(1, 2, 3).collect(Collectors.toList());
        List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
        removeAndPrint(collection);
        removeAndPrint(list);
    }

    ////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
//        testOverride();
        testCollections();
    }
}