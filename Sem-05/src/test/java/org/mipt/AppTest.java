package org.mipt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AppTest {
    @Test
    void helloTest() {
        Assertions.assertThat(2 + 2).isEqualTo(4);
    }

    @Test
    void strTest() {
        String a = "Hello, world!";
        String b = "Hello, world!";
        Assertions.assertThat(a).isEqualTo(b);
    }

    @Test
    void otherStrTest() {
        String a = "Hello, world!";
        Assertions.assertThat(a)
                .startsWith("Hello")
                .contains("llo, ")
                .endsWith("!");
    }

    @BeforeAll
    static void setUp(){
        System.out.println("Testing started");
    }

    @BeforeEach
    void before(){
        System.out.println("This method is called before each test");
    }

    @Test
    void exceptionTest(){
        assertThatThrownBy(() -> {
            List<String> list = Arrays.asList("String one", "String two");
            list.get(2);
        }).isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessageContaining("Index 2 out of bounds for length 2");
    }


    @RepeatedTest(5)
    void repeatedTest() {
        System.out.println("You will see this message five times");
    }

    @AfterEach
    void after(){
        System.out.println("This method is called after each test");
    }

    @AfterAll
    static void Finalize(){
        System.out.println("Testing finished");
    }
}
