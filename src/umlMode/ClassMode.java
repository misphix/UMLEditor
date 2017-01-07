package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlClass;
import umlObject.UmlObject;

import java.util.List;

public class ClassMode extends UmlMode {
    public ClassMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        UmlClass newClass = new UmlClass(event.getX(), event.getY());
        elements.add(newClass);
    }
}
