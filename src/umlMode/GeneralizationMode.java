package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;

import java.util.List;

public class GeneralizationMode extends UmlMode {
    public GeneralizationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        // TODO
        System.out.println("Generalization");
    }
}
