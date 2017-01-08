package mode;

import javafx.scene.input.MouseEvent;
import object.SelectionArea;
import object.UmlObject;

import java.util.List;

public abstract class UmlMode {
    protected final List<UmlObject> elements;

    protected UmlMode(List<UmlObject> elements) {
        this.elements = elements;
    }

    public void mousePressEvent(MouseEvent event) {

    }

    public abstract void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea);
    public abstract void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea);
}