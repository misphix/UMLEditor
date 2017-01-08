package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlCompositionLine;
import umlObject.UmlObject;

import java.util.List;

public class CompositionMode extends ConnectionMode {
    public CompositionMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event) {
        super.mousePressEvent(event);
        if (start != null) {
            currentLine = new UmlCompositionLine(start, end);
            elements.add(currentLine);
        }
    }
}
