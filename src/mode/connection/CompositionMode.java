package mode.connection;

import javafx.scene.input.MouseEvent;
import object.line.UmlCompositionLine;
import object.UmlObject;

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
