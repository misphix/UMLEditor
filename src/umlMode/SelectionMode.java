package umlMode;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import umlObject.SelectionArea;
import umlObject.UmlObject;

import java.util.ArrayList;
import java.util.List;

public class SelectionMode extends UmlMode {
    private Point2D startPoint, previousMousePoint;

    public SelectionMode(List<UmlObject> elements) {
        super(elements);
    }

    @Override
    public void mousePressEvent(MouseEvent event, SelectionArea selectionArea) {
        startPoint = new Point2D(event.getX(), event.getY());
        previousMousePoint = startPoint;

        selectObject();
    }


    private void selectObject() {
        for (int index = elements.size() - 1; index >= 0; --index) {
            UmlObject object = elements.get(index);

            if (object.contain(startPoint)) {
                if (object.isSelected()) {
                    return;
                }
                unSelectAll();
                object.beSelected();
                pushToFront(object);
                return;
            }
        }
        unSelectAll();
    }

    private void unSelectAll() {
        for (UmlObject object : elements) {
            object.unSelected();
        }
    }

    private void pushToFront(UmlObject object) {
        elements.remove(object);
        elements.add(object);
    }

    @Override
    public void mouseDraggedEvent(MouseEvent event, SelectionArea selectionArea) {
        if (getSelectedObjects().size() > 0) {
            moveSelectedObject(event);
        } else {
            double width = event.getX() - startPoint.getX();
            double height = event.getY() - startPoint.getY();

            selectionArea.setVisible(true);
            selectionArea.setPosition(startPoint.getX(), startPoint.getY());
            selectionArea.setWidth(width);
            selectionArea.setHeight(height);
        }
    }

    private void moveSelectedObject(MouseEvent event) {
        List<UmlObject> selectedObject = getSelectedObjects();
        double dx = event.getX() - previousMousePoint.getX();
        double dy = event.getY() - previousMousePoint.getY();
        previousMousePoint = new Point2D(event.getX(), event.getY());

        for (UmlObject object : selectedObject) {
            assert object != null : "Selected object is null";
            object.movePosition(dx, dy);
        }
    }

    private List<UmlObject> getSelectedObjects() {
        List<UmlObject> selectedObject = new ArrayList<>();

        for (UmlObject object : elements) {
            if (object.isSelected()) {
                selectedObject.add(object);
            }
        }
        return selectedObject;
    }

    @Override
    public void mouseReleasedEvent(MouseEvent event, SelectionArea selectionArea) {
        if (selectionArea.isVisible()) {
            selectObjects(selectionArea);

            selectionArea.setVisible(false);
        }
    }

    private void selectObjects(SelectionArea selectionArea) {
        Bounds selectionBounds = new BoundingBox(selectionArea.getX(), selectionArea.getY(), selectionArea.getWidth(), selectionArea.getHeight());

        for (UmlObject object : elements) {
            Bounds objectBounds = new BoundingBox(object.getX(), object.getY(), object.getWidth(), object.getHeight());
            if (selectionBounds.contains(objectBounds)) {
                object.beSelected();
            }
        }

        for (UmlObject object : getSelectedObjects()) {
            pushToFront(object);
        }
    }
}
