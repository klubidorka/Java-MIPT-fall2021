package abstract_factory.buttons;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Windows button rendered");
    }
}
