class OuterClass {
    private static int N;
    private int K;
    private static long time;
    public StaticNestedClass staticNestedClass;
    private final String str;

    void setNested(StaticNestedClass nested) {
        staticNestedClass = nested;
    }

    OuterClass() {
        str = "Egor";
    }


    public static void printTime() {
        System.out.println(time);
    }

    class NestedClass {
        // static int v = 10; // forbidden
        void setOuterN() {
            OuterClass.N = 12;
            K = 3; // OK
        }

        void getReferenceToOuter() {
            OuterClass myOuter = OuterClass.this;
        }
    }

    static class StaticNestedClass {
        StaticNestedClass() {
            System.out.println(System.currentTimeMillis());
//            time = System.currentTimeMillis();
        }
    }


    void localClassDemo() {

    }
}


// Множественное наследование классов запрещено, но с помощью вложенных классов можно добиться поведения, похожего
// на множественное наследование

class SomeClass {
    static int A = 1;
}

class SomeClass2 {
    static int A = 2;

}

class OuterClass2 extends SomeClass2 {
    static class InnerClass2 extends SomeClass {
        public static void main(String[] args) {
            System.out.println(OuterClass2.A);
            System.out.println(InnerClass2.A);
        }
    }
}


// Множественное наследование интерфейсов
interface A {
}

interface B {
}

interface C extends A, B {
}

class D implements A, B {

}

class LocalClassDemo {

    static void foo() {
        class LocalClass {
            void print() {
                System.out.println("Hello");
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println("Destroyed");
            }
        }
        System.out.println("Odin");
        LocalClass localClass = new LocalClass();
        localClass.print();
    }

    public static void main(String[] args) {
        foo();
        System.gc();
        System.out.println("Dva");
    }
}

public class ClassesDemo {
    static void constructors() {
        // Пример создания вложенноно и статического вложенного классов
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();
        OuterClass outerClass = new OuterClass();
        OuterClass.NestedClass nestedClass = outerClass.new NestedClass();
    }

    public static void main(String[] args) {
        constructors();

        // Пример использования внонимного класса
        new SomeClass() {
            void print() {
                System.out.println("Anonymous class");
            }
        }.print();

        // Создаем и запускаем поток при помощи аномимного класса
        new Thread() {
            @Override
            public void run() {
                System.out.println("Hello from other thread");
            }
        }.start();

        // То же самое, но через лямбду
        new Thread(() -> System.out.println("Hello from other thread")).start();
    }
}
