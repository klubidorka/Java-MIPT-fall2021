import java.util.ArrayList;
import java.util.List;

public class AbstractClassAndInterface {
    static void walkHorses() {
        Horse roman = new RomanHorse();
        Horse egor = new EgorHorse();
        List<Horse> horses = new ArrayList<>(2);
        horses.add(roman);
        horses.add(egor);

        for (Horse horse : horses) {
            System.out.println(horse.name());
        }
    }

    void print(List<Integer> list){
        for (Integer it : list){
            System.out.println(it);
        }
    }

    /**
     * If we want to create instance of abstract class, we need to implement all abstract methods
     */
    static void createAbstract() {
        AbstractClass abstractClass = new AbstractClass() {
            @Override
            void someMethod() {
                System.out.println("Implementation");
            }
        };
    }

    public static void main(String[] args) {
        walkHorses();
    }
}

abstract class AbstractClass {
    int numberDefault;
    private int numberPrivate;
    static protected int numberProtected;
    final public int numberPublic;

    void methodDefault() {
    }

    private void methodPrivate() {
    }

    final protected void methodProtected() {
    }

    public static void methodPublic() {
    }

    // abstract method i.e method without implementation
    abstract void someMethod();

    // Abstract class can have constructor
    AbstractClass() {
        numberPublic = 715;
    }
}

interface Horse {
    public static final String nameString = "My name is ";

    // All methods are public abstract by default
    // abstract final is illegal
    String getName();

    /**
     * JDK8+ provides default and static methods
     */
    default String name(){
        return getPrefix() + getName();
    }

    /**
     * In Java9+ private methods are available
     */
    private String getPrefix() {
        return nameString;
    }
}

class RomanHorse implements Horse {
    @Override
    public String getName() {
        return "Roman";
    }
}

class EgorHorse implements Horse {
    @Override
    public String getName() {
        return "Egor";
    }
}
