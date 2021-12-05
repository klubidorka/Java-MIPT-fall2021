import org.junit.jupiter.api.RepeatedTest;


public class AppTest {
    @RepeatedTest(10)
    void helloTest() throws InterruptedException {
        ConcurrentDataStructuresDemo.main(new String[0]);
    }
}