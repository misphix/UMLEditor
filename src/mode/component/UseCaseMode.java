package mode.component;

import javafx.scene.input.MouseEvent;
import object.SelectionArea;
import object.UmlObject;
import object.shape.UmlUseCase;

import java.util.List;

public class UseCaseMode extends ComponentMode {
    public UseCaseMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        UmlUseCase newUseCase = new UmlUseCase(event.getX(), event.getY());
        elements.add(newUseCase);
    }
}
