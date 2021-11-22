public class Final {
}

class FinalFieldInitialization {
    private Object object1;
    private final Object object2;

    FinalFieldInitialization() {
        object1 = new Object();
        object2 = new Object(); // Guaranteed that object2 is constructed before constructor returns, cause it's final
    }
}

class Problem {
    Object object;

    Problem() {
        object = null;
    }

    // What is wrong here?
    public Object getObject() {
        if (object == null) {
            synchronized (this) {
                if (object == null) {
                    object = new Object();
                }
            }
        }
        return object;
    }
}