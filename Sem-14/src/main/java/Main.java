import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
}


// Q-2
//--------------------------------------------------------------------------//
class A {
    public void method() {
    }
}

class B extends A {
//    private void method() {
//    }
}


// Q-3
//--------------------------------------------------------------------------//
class Q3 {
    public static void main(String[] args) {
        C instance = new D();
        instance.staticMethod();
        instance.method();
    }
}

class C {
    public static void staticMethod() {
        System.out.println("C");
    }

    public void method() {
        System.out.println("C");
    }
}

class D extends C {
    public static void staticMethod() {
        System.out.println("D");
    }

    public void method() {
        System.out.println("D");
    }
}

// Puzzler #1
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Что произойдет?
 * <p>
 * A. IllegalArgumentException – нельзя создавать семафор с негативным балансом;
 * B. UnsupportedOperationException – можно создать семафор с негативным балансом, но нельзя вызывать на нем drainPermits;
 * C. 0 – drainPermits при негативном балансе оставит ноль пермитов;
 * D. -42 — drainPermits при негативном балансе оставит столько же, сколько было, потому что сливать нечего.
 */
class PerfectRobbery {
    private final Semaphore bankAccount = new Semaphore(-42);

    public static void main(String[] args) {
        PerfectRobbery perfectRobbery = new PerfectRobbery();
        perfectRobbery.takeAllMoney();
        perfectRobbery.checkBalance();
    }

    public void takeAllMoney() {
        bankAccount.drainPermits();
    }

    public void checkBalance() {
        System.out.println(bankAccount.availablePermits());
    }
}

// Puzzler #2
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Что будет напечатано?
 */
class SingletonTest {
    public static void main(String[] args) {
        System.out.println(Collections.emptyList() == Collections.emptyList());
        System.out.println(Collections.emptyIterator() == Collections.emptyIterator());
        System.out.println(Stream.empty() == Stream.empty());
    }
}

// Puzzler #3
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Верно ли, что мы увидим элементы в том же самом порядке, в котором мы их завели? Зависит ли ответ от версии java?
 * A. Порядок объявления сохраняется
 * B. Порядок неизвестен, но сохраняется между запусками
 * C. Порядок неизвестен и меняется при каждом перезапуске JVM
 * D. Порядок неизвестен и меняется при каждой распечатке
 */
class PerfectRobbery2 {
    public static void main(String[] args) {
        Set<String> accounts = Set.of("Gates", "Buffett", "Bezos", "Zuckerberg");
        System.out.println("accounts = " + accounts);
    }
}

// Puzzler #4
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Что произойдет?
 * <p>
 * A. Все умерли
 * B. Только четные умерли
 * C. Все выжили
 * D. Только нечетные умерли
 * E. Все ответы верны
 */
class KillThemAll {
    static void killThemAll(Collection<String> expendables) {
        Iterator<String> heroes = expendables.iterator();
        heroes.forEachRemaining(e -> {
            if (heroes.hasNext()) {
                heroes.next();
                heroes.remove();
            }
        });
        System.out.println(expendables);
    }

    public static void main(String[] args) {
        killThemAll(new ArrayList<>(List.of("N", "S", "W", "S", "L", "S", "L", "V")));
        killThemAll(new LinkedList<>(List.of("N", "S", "W", "S", "L", "S", "L", "V")));
        killThemAll(new ArrayDeque<>(List.of("N", "S", "W", "S", "L", "S", "L", "V")));
        killThemAll(new TreeSet<>(List.of("N", "S", "W", "S", "L", "S", "L", "V")));
    }
}

// Puzzler #5
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Вопрос: скомпилируется это все или нет?
 * <p>
 * A. Оба скомпилируются
 * B. Ламбда скомпилируется, ссылка на метод – нет
 * C. Ссылка на метод скомпилируется, лямбда – нет
 * D. Не функциональный интерфейс!
 */

@FunctionalInterface
interface OriginalPredicate<T> {
    boolean test(T t);
}

@FunctionalInterface
interface CopyCatPredicate {
    <T> boolean test(T t);
}

class Difference {
    public static void main(String[] args) {
        OriginalPredicate<Object> lambda = (Object obj) -> "adidas".equals(obj);
        OriginalPredicate<Object> methodRef = "adidas"::equals;

        // CopyCatPredicate lambda = (Object obj) -> "adadas".equals(obj);
        // CopyCatPredicate methodRef = "adadas"::equals;
    }
}


// Puzzler #6
// Source: https://habr.com/ru/company/jugru/blog/352438/
//--------------------------------------------------------------------------//

/**
 * Что будет напечатано?
 * <p>
 * A. Отсортированные и отфильтрованные [DotNext, HolyJS, Joker]
 * B. Ровно то же, что было в начале [Joker, DotNext, HolyJS, HolyJS, DotNext, Joker]
 * C. В начальном порядке, но отфильтрованные [Joker, DotNext, HolyJS]
 * D. Отсортированные, но не отфильтрованные [DotNext, DotNext, HolyJS, HolyJS, Joker, Joker]
 */

class Conference {
    public static void main(String[] args) {
        List<String> list = Stream.of("Joker", "DotNext", "HolyJS", "HolyJS", "DotNext", "Joker")
                .sequential()
                .filter(new TreeSet<>()::add)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}