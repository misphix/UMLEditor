package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.List;

public class GeneralizationMode extends UmlMode {
    public GeneralizationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // TODO
        System.out.println("Generalization");
    }
}
