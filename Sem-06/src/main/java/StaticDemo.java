public class StaticDemo {
    public static String name;
    public String name2;

    // Блок статической инициализации
    static {
        name = "Kerserk";
        System.out.println(name);
        System.out.println("Static");
    }

    StaticDemo() {
        System.out.println("Constructor");
        System.out.println(name);
    }
}


class Demo {
    public static void main(String[] args) {
        StaticDemo staticDemo = new StaticDemo();
    }
}
