package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.buttons.MacosButton;
import abstract_factory.icons.Icon;
import abstract_factory.icons.MacosIcon;

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
