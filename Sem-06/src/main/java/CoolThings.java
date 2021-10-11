import java.io.IOException;

enum Name {
    ANDREY,
    ZHANEL,
    DOKTOR_LIVSY,
}

/**
 * This is demo of exception and enums
 */
public class CoolThings {

    /**
     * @throws IOException Тут мы объявили, что может быть выброшено IOException, хотя это не так
     *                     Ошибки компиляции нет, но если мы вызовем этот метод, то будет необходимо обработать IOException
     */
    static void foo() throws IOException {
        // Бросаем unchecked исключение
        throw new RuntimeException();
    }

    /**
     * Main method
     *
     * @throws IOException if smth bad happened
     */
    public static void main(String[] args) throws IOException {
        try {
            foo();
        } catch (IOException ignored) {
        }
        Name name = Name.ZHANEL;
        switch (name) {
            case ANDREY:
                System.out.println("Hey, Andrew");
                break;
            case ZHANEL:
                System.out.println("Privet Zhanel");
                break;
            case DOKTOR_LIVSY:
                System.out.println("AXAXAXAXXAXAXAXXAXAXAXAXAXAXAXAXAXAXAXAXAXXAXAX");
                break;
        }
    }
}
