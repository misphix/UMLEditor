package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;

import java.util.List;

public class AssociationMode extends UmlMode {
    public AssociationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        // TODO
        System.out.println("Association");
    }
}
