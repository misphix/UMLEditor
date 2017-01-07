package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.List;

public abstract class UmlMode {
    final List<UmlObject> elements;

    public UmlMode(List<UmlObject> elements) {
        this.elements = elements;
    }

    public void mousePressEvent(MouseEvent event) {

    }

    public void mouseDraggedEvent(MouseEvent event) {

    }

    public abstract void mouseReleasedEvent(MouseEvent event);
}