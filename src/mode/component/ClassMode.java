package mode.component;

import javafx.scene.input.MouseEvent;
import object.SelectionArea;
import object.shape.UmlClass;
import object.UmlObject;

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
