package mode.connection;

import javafx.scene.input.MouseEvent;
import object.umlLine.UmlGeneralizationLine;
import object.UmlObject;

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
