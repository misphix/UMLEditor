package umlMode;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import umlObject.UmlObject;

import java.util.ArrayList;
import java.util.List;

public class SelectionMode extends UmlMode {
    private Point2D startPoint, previousMousePoint;

    public SelectionMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event) {
        startPoint = new Point2D(event.getX(), event.getY());
        previousMousePoint = startPoint;

        unSelectAll();
        selectObject();
    }

    private void unSelectAll() {
        for (UmlObject object : elements) {
            object.unSelected();
        }
    }

    private void selectObject() {
        for (int index = elements.size() - 1; index >= 0; --index) {
            UmlObject object = elements.get(index);
            if (object.contain(startPoint)) {
                object.beSelected();
                pushToFront(object);
                break;
            }
        }
    }

    private void pushToFront(UmlObject object) {
        elements.remove(object);
        elements.add(object);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event) {
        moveSelectedObject(event);
    }

    private void moveSelectedObject(MouseEvent event) {
        List<UmlObject> selectedObject = getSelectedObject();
        double dx = event.getX() - previousMousePoint.getX();
        double dy = event.getY() - previousMousePoint.getY();
        previousMousePoint = new Point2D(event.getX(), event.getY());

        for (UmlObject object : selectedObject) {
            assert object != null : "Selected object is null";
            object.movePosition(dx, dy);
        }
    }

    private List<UmlObject> getSelectedObject() {
        List<UmlObject> selectedObject = new ArrayList<>();

        for (UmlObject object : elements) {
            if (object.isSelected()) {
                selectedObject.add(object);
            }
        }
        return selectedObject;
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event) {
        // TODO
        System.out.println("Select");
    }
}
