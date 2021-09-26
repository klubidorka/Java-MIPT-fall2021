package abstract_factory.factories;

import abstract_factory.buttons.Button;
import abstract_factory.icons.Icon;

public interface GUIFactory {
    Button createButton();

    Icon createIcon();
}
