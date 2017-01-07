package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.List;

public class AssociationMode extends UmlMode {
    public AssociationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // TODO
        System.out.println("Association");
    }
}
