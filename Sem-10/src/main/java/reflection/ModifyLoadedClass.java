package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ModifyLoadedClass {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> cls = Class.forName(TestClass.class.getName());

        Class<?> [] params = {int.class};
        TestClass testClass = (TestClass) cls.getConstructor(params).newInstance(1);

        // changing values of fields
        Field field = cls.getDeclaredField("field");
        field.setAccessible(true);
        field.setInt(testClass, 13);
        field.set(testClass, 13);
    }
}

class TestClass {
    private int field;

    private int sum(int a, int b) {
        return a + b;
    }

    public TestClass(int field) {
        this.field = field;
    }
}