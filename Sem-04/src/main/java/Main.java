import app.App;
import factories.GUIFactory;
import factories.MacosGUIFactory;
import factories.WindowsGUIFactory;

public class Main {

    private static App configureApp() {
        GUIFactory guiFactory;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            guiFactory = new MacosGUIFactory();
        } else if (os.contains("win")) {
            guiFactory = new WindowsGUIFactory();
        } else {
            throw new RuntimeException("OS is not supported");
        }
        return new App(guiFactory);
    }

    public static void main(String[] args) {
        App app = configureApp();
        app.prepareGUI();
    }
}
