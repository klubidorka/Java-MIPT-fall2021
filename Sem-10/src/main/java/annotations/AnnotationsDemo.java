package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

// Source https://habr.com/ru/post/139736/

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Name {
    String name();

    String type() default "string";
}

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface StartObject {
}

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface StopObject {
}

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@interface ControlledObject {
    String name();
}

public class AnnotationsDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cl = Class.forName(Cookies.class.getName());
        if (!cl.isAnnotationPresent(ControlledObject.class)) {
            System.err.println("no annotation");
        } else {
            System.out.println("class annotated ; name  -  " + cl.getAnnotation(ControlledObject.class));
        }
        boolean hasStart = false;
        boolean hasStop = false;
        Method[] method = cl.getMethods();
        for (Method md : method) {
            if (md.isAnnotationPresent(StartObject.class)) {
                hasStart = true;
            }
            if (md.isAnnotationPresent(StopObject.class)) {
                hasStop = true;
            }
            if (hasStart && hasStop) {
                break;
            }
        }
        System.out.println("Start annotation  - " + hasStart + ";  Stop annotation  - " + hasStop);
    }
}
