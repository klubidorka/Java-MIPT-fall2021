package SOLID.OCP;

/**
 * Class should be open for extension and closed for modification (except fixing bugs)
 */
public class Car {
    private String model;
    private String color;
    // getters, setters, constructor etc
}

class coolCar extends Car {
    private String owner;
}
