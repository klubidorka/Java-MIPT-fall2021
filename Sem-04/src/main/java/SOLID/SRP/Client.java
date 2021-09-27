package SOLID.SRP;

/**
 *  Class should only have one responsibility and only one reason to change
 *  +++ Such class is easier to cover with tests
 *  +++ Less functionality in class leads to fewer dependencies
 *  +++ Small classes are easier to search and understand
 */
public class Client {
    private final String name;
    private final String email;

    Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    // violates SRP
    public void printInfoToConsole() {
        System.out.println("Client " + name + ". Their email is " + email);
    }
}
