package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlGeneralizationLine;
import umlObject.UmlObject;

import java.util.List;

public class GeneralizationMode extends ConnectionMode {
    public GeneralizationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event) {
        super.mousePressEvent(event);
        if (start != null) {
            currentLine = new UmlGeneralizationLine(start, end);
            elements.add(currentLine);
        }
    }
}
