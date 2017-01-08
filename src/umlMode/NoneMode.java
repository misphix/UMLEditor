package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;

import java.util.List;

public class NoneMode extends UmlMode {
    public NoneMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        // FIXME
        System.out.println("None");
    }
}
