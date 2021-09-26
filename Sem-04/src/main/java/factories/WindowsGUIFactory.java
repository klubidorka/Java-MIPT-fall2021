package factories;

import buttons.Button;
import buttons.WindowsButton;
import icons.Icon;
import icons.WindowsIcon;

public class WindowsGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Icon createIcon() {
        return new WindowsIcon();
    }
}