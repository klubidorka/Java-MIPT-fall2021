/**
 * All classes implicitly inherit from Object.
 * Here we do that explicitly for demonstration
 */
public class ObjectSubclass extends Object {
    @Override
    public String toString(){
        return "Subclass of Object";
    }

    /**
     * We must provide that
     * if
     * a.equals(b) == true
     * then
     * a.hashCode() == b.hashCode()
     */
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }
}

class Test{
    public static void main(String[] args) {
        ObjectSubclass test = new ObjectSubclass();
        System.out.println(test);
//        System.out.println(test.getClass());
    }
}


