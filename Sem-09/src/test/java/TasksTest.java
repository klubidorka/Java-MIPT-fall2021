import streams.tasks.Tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TasksTest {
    @Test
    void task1() {
        Tasks<String> stringTask = new Tasks<>();
        assertThat(stringTask.task1(Stream.of("a", "a", "b"), "a")).isEqualTo(2);
        assertThat(stringTask.task1(Stream.of("a", "a", "b"), "c")).isEqualTo(0);

        Tasks<Integer> intTask = new Tasks<>();
        assertThat(intTask.task1(Stream.of(1, 2, 3, 2, 2), 2)).isEqualTo(3);
        assertThat(intTask.task1(Stream.of(1, 2, 3), 4)).isEqualTo(0);
    }

    @Test
    void task2() {
        Tasks<Double> doubleTask = new Tasks<>();
        assertThat(doubleTask.task2(Arrays.asList(1D, 2D, 3D, 15D))).isEqualTo(15);
        assertThat(doubleTask.task2(Collections.emptyList())).isEqualTo(null);
    }

    @Test
    void task3() {
        Tasks<Integer> intTask = new Tasks<>();
        assertThat(intTask.task3(Stream.of(1, 2, 3, 15))).isEqualTo(Arrays.asList(2, 3));
        assertThat(intTask.task3(Stream.of(12, 4, 7, 15, 123, 12, 6, 345))).isEqualTo(Arrays.asList(4, 7));
    }

    @Test
    void task4() {
        Tasks<Integer> intTask = new Tasks<>();
        assertThat(intTask.task4(Stream.of(1, 2, 3, 4), 4)).isTrue();
        assertThat(intTask.task4(Stream.of(1, 2, 3, 4), 5)).isFalse();
    }

    @Test
    void task5() {
        Tasks<String> stringTasks = new Tasks<>();
        assertThat(stringTasks.task5(Stream.of("abc", "abc", "a", "aa"))).isEqualTo(Arrays.asList("a", "aa", "abc"));
        Tasks<Integer> intTasks = new Tasks<>();
        assertThat(intTasks.task5(Stream.of(3, 2, 4, 2))).isEqualTo(Arrays.asList(2, 3, 4));
        assertThat(stringTasks.task5(Stream.of())).isEqualTo(Collections.emptyList());
    }
}
