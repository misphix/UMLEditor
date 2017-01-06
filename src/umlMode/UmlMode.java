package umlMode;

import javafx.scene.input.MouseEvent;

public abstract class UmlMode {
    public void mousePressEvent(MouseEvent event) {

    }

    public void mouseDraggedEvent(MouseEvent event) {

    }

    public abstract void mouseReleasedEvent(MouseEvent event);
}