package reflection;

import java.io.File;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class InspectLoadedClass {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?> loadedClass = loadClass();

        Object instance = loadedClass.getDeclaredConstructor().newInstance();

        // print class name
        System.out.println("Class name is:");
        System.out.println(getClassName(loadedClass));

        // print all the fields
        System.out.println("\nClass fields are:");
        Field[] fields = getClassFields(loadedClass);
        for (Field field : fields) {
            System.out.println(field);
        }

        // print all the methods
        System.out.println("\nClass methods are:");
        Method[] methods = getClassMethods(loadedClass);
        for (Method method : methods) {
            System.out.println(method);
        }

        // get field contents
        System.out.println("\nClass field contents:");
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldContent = (String) field.get(instance);
            System.out.println(fieldContent);
        }

        // call methods
        System.out.println("\nResults of methods call:");
        for (Method method : methods) {
            method.setAccessible(true);
            Object obj = method.invoke(instance);
            System.out.println("Return value of " + method);
            System.out.println(obj);
        }
    }

    /**
     * Loads class SecretClass from SecretClass.class at runtime
     */
    static Class<?> loadClass() throws MalformedURLException, ClassNotFoundException {
        String dir = System.getProperty("user.dir");
        URL url = new File(dir + "/Compiled").toURI().toURL();
        ClassLoader cl = new URLClassLoader(new URL[]{url});
        return Class.forName("SecretClass", true, cl);
    }

    /**
     * Returns class name
     */
    static String getClassName(Class<?> cls) {
        return cls.getName();
    }

    /**
     * Returns fully qualified names of all the class fields
     */
    static Field[] getClassFields(Class<?> cls) {
        return cls.getDeclaredFields();
    }

    /**
     * Returns fully qualified names of all the class methods
     */
    static Method[] getClassMethods(Class<?> cls) {
        return cls.getDeclaredMethods();
    }
}
