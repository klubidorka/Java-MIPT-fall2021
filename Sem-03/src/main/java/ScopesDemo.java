/**
 * Only one public class in file. File name and class name are the same
 */
public class ScopesDemo {
    int numberDefault;
    private int numberPrivate;
    protected int numberProtected;
    public int numberPublic = 715; // Default value

    /**
     * Package private method. Available from package
     */
    void methodDefault() {
    }

    /**
     * Private method. Available from this class only
     */
    private void methodPrivate() {
    }

    /**
     * Protected method. Available from this class, its subclasses and package members
     */
    protected void methodProtected() {
    }

    /**
     * Public method. Available from any place where the class is available
     */
    public void methodPublic() {
    }
}

class ScopesDemoSubclass extends ScopesDemo {
    void scopesTest() {
        // Default, protected and private fields are available from subclass
        numberDefault += 1;
        numberProtected += 1;
        numberPublic += 1;
        // numberPrivate += 1 // Private field of other class is not available


        // Same for methods
        methodDefault();
        methodProtected();
        methodPublic();
        // methodPrivate()
    }
}

/**
 * Fields and methods can be static. Static methods and fields belong to class, not to instance
 */
class StaticDemo {
    static int numberDefault;
    static private int numberPrivate;
    static protected int numberProtected;
    static public int numberPublic;

    static void methodDefault() {
    }

    static private void methodPrivate() {
    }

    static protected void methodProtected() {
    }

    static public void methodPublic() {
    }
}

class StaticAccessDemo {
    void someMethod() {
        // Non-static fields and methods can be accessed via instance
        ScopesDemo instance = new ScopesDemo();
        instance.numberDefault += 1;
        instance.methodProtected();

        // Static methods and fields can be accessed via class or instance (better to access via class)
        StaticDemo.numberDefault += 1;
        StaticDemo.methodPublic();
    }
}
