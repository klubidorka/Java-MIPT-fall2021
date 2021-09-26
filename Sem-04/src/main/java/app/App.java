package app;

import buttons.Button;
import factories.GUIFactory;
import icons.Icon;

public class App {
    private Button button;
    private Icon icon;

    public App(GUIFactory guiFactory) {
        button = guiFactory.createButton();
        icon = guiFactory.createIcon();
    }

    public void prepareGUI() {
        button.render();
        icon.render();
    }
}
