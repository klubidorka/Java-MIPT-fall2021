package factories;

import buttons.Button;
import icons.Icon;

public interface GUIFactory {
    Button createButton();

    Icon createIcon();
}
