package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.List;

public class CompositionMode extends UmlMode {
    public CompositionMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // TODO
        System.out.println("Compositon");
    }
}
