package abstract_factory.icons;

public class MacosIcon implements Icon {
    @Override
    public void render() {
        System.out.println("Macos icon rendered");
    }
}
