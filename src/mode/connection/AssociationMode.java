package mode.connection;

import javafx.scene.input.MouseEvent;
import object.line.UmlLine;
import object.UmlObject;

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
