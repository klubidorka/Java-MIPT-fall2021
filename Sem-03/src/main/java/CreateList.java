import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateList {

    CreateList() {
        // init, then add
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);

        // double brace initialization
        List<Integer> list2 = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }};

        // Create immutable list using Arrays
        List<Integer> list3 = Arrays.asList(1, 2, 3);

        // Create immutable list using List
        List<Integer> list4 = List.of(1, 2, 3); // Java 9
        // list4.add(12); // UnsupportedOperationException

        // Java8 stream API
        List<Integer> list5 = Stream.of(1, 2, 3).collect(Collectors.toList());
    }
}


