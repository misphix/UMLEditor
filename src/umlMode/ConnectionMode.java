package umlMode;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import umlObject.*;

import java.util.List;

abstract class ConnectionMode extends UmlMode {
    UmlLine currentLine = null;
    UmlObject startObject = null;
    Port start = null, end = null;

    ConnectionMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event) {
        Point2D pressedPoint = new Point2D(event.getX(), event.getY());
        UmlObject object = selectObject(pressedPoint);

        try {
            if (object == null) {
                throw new NullPointerException();
            }
            startObject = object;
            start = object.getClosestPort(pressedPoint);
            end = start;
        } catch (NullPointerException e) {
            resetLine();
        }
    }


    private UmlObject selectObject(Point2D pressedPoint) {

        for (int index = elements.size() - 1; index >= 0; --index) {
            UmlObject object = elements.get(index);

            if (object == startObject) {
                continue;
            }

            if (object.contain(pressedPoint)) {
                return object;
            }
        }

        return null;
    }

    private void resetLine() {
        start = null;
        end = null;
        currentLine = null;
        startObject = null;
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {
        if (start != null) {
            end = new Port(event.getX() - Constants.PORT_WIDTH / 5, event.getY() - Constants.PORT_WIDTH / 5);
            currentLine.setEnd(end);
        }
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        if (start != null) {
            Point2D releasePoint = new Point2D(event.getX(), event.getY());
            UmlObject object = selectObject(releasePoint);

            try {
                if (object == null) {
                    throw new NullPointerException();
                }
                end = object.getClosestPort(releasePoint);
                if (end  == null) {
                    throw new NullPointerException();
                }
                currentLine.setEnd(end);
            } catch (NullPointerException e) {
                elements.remove(currentLine);
            }

            resetLine();
        }
    }
}
