package mode.component;

import javafx.scene.input.MouseEvent;
import mode.UmlMode;
import object.SelectionArea;
import object.UmlObject;

import java.util.List;

abstract class ComponentMode extends UmlMode {
    ComponentMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {

    }
}
