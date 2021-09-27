package abstract_factory.app;

import abstract_factory.buttons.Button;
import abstract_factory.factories.GUIFactory;
import abstract_factory.icons.Icon;

public class App {
    private final Button button;
    private final Icon icon;

    public App(GUIFactory guiFactory) {
        button = guiFactory.createButton();
        icon = guiFactory.createIcon();
    }

    public void prepareGUI() {
        button.render();
        icon.render();
    }
}
