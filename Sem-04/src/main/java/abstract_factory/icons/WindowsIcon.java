package abstract_factory.icons;

public class WindowsIcon implements Icon {
    @Override
    public void render() {
        System.out.println("Windows icon rendered");
    }
}
