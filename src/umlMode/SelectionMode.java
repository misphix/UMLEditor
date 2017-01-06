package umlMode;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class SelectionMode extends UmlMode {
    private Point2D startPoint;

    @Override
    public void mousePressEvent(MouseEvent event) {
        startPoint = new Point2D(event.getX(), event.getY());
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event) {
        // TODO
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // TODO
        System.out.println("Select");
    }
}
