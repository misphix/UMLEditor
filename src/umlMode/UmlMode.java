package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;

import java.util.List;

public abstract class UmlMode {
    final List<UmlObject> elements;

    public UmlMode(List<UmlObject> elements) {
        this.elements = elements;
    }

    public void mousePressEvent(MouseEvent event, SelectionArea selectionArea) {

    }

    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {

    }

    public abstract void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea);
}