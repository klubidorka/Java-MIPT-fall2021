package factories;

import buttons.Button;
import buttons.MacosButton;
import icons.Icon;
import icons.MacosIcon;

public class MacosGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacosButton();
    }

    @Override
    public Icon createIcon() {
        return new MacosIcon();
    }
}
