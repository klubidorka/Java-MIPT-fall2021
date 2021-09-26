package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.WindowsButton;
import abstract_factory.icons.Icon;
import abstract_factory.icons.WindowsIcon;

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