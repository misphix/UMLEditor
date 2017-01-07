package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;
import umlObject.UmlUseCase;

import java.util.List;

public class UseCaseMode extends UmlMode {
    public UseCaseMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        UmlUseCase newUseCase = new UmlUseCase(event.getX(), event.getY());
        elements.add(newUseCase);
    }
}
