package SOLID.LSP;

/**
 * if class A is a subtype of class B, we should be able to replace B with A without
 * disrupting the behavior of our program
 */
public class Shape {
    public long area(){
        long area = 0;
        // calculate area with integrals
        return area;
    }
}

class Square extends Shape {
    private long side;

    @Override
    public long area() {
        return side * side;
    }
}

class Point extends Shape {
    @Override
    public long area() {
        // LSP violation
        throw new RuntimeException();
    }
}
