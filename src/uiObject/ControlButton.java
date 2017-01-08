package uiObject;

import javafx.scene.control.ToggleButton;
import umlMode.UmlMode;

public class ControlButton extends ToggleButton {
    private final UmlMode mode;

    public ControlButton(String text, UmlMode mode) {
        super(text);
        this.mode = mode;
    }

    public UmlMode getMode() {
        return mode;
    }
}
