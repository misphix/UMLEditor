package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;
import umlObject.UmlUseCase;

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
