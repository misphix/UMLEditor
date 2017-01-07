package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.List;

public class NoneMode extends UmlMode {
    public NoneMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // FIXME
        System.out.println("None");
    }
}
