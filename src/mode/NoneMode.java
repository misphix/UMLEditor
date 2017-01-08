package mode;

import javafx.scene.input.MouseEvent;
import object.SelectionArea;
import object.UmlObject;

import java.util.List;

public class NoneMode extends UmlMode {
    public NoneMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {

    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        // FIXME
        System.out.println("None");
    }
}
