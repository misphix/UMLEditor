package umlMode;

import javafx.scene.input.MouseEvent;
import umlObject.UmlLine;
import umlObject.UmlObject;

import java.util.List;

public class AssociationMode extends ConnectionMode {
    public AssociationMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event) {
        super.mousePressEvent(event);
        if (start != null) {
            currentLine = new UmlLine(start, end);
            elements.add(currentLine);
        }
    }
}
