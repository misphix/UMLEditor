package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlClass;
import umlObject.UmlObject;

import java.util.List;

public class ClassMode extends ComponentMode {
    public ClassMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        UmlClass newClass = new UmlClass(event.getX(), event.getY());
        elements.add(newClass);
    }
}
