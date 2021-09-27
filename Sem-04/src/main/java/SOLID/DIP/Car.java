package SOLID.DIP;

/**
 * Instead of high-level modules depending on low-level modules, both will depend on abstractions
 */

////////////////////////////////////
class VolkswagenEngine {
}

class PirelliTires {
}

class DIPViolatingCar {
    VolkswagenEngine engine;
    PirelliTires tires;
}
////////////////////////////////////

interface Engine {
}

class PorscheEngine implements Engine {
}

class UAZEngine implements Engine {
}

interface Tires {
}

class DunlopTires implements Tires {
}

class ContinentalTires implements Tires {
}


public class Car {
    Engine engine;
    Tires tires;

    public Car(Engine engine, Tires tires) {
        this.engine = engine;
        this.tires = tires;
    }
}
