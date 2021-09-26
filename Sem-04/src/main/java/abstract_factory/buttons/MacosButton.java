package abstract_factory.buttons;

public class MacosButton implements Button {
    @Override
    public void render() {
        System.out.println("Macos button rendered");
    }
}
