package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class UmlGroup extends UmlShape {
    private final List<UmlObject> elements;

    public UmlGroup(List<UmlObject> groupElements) {
        elements = groupElements;
        setX();
        setY();
        setWidth();
        setHeight();
    }

    private void setX() {
        double minX = Double.MAX_VALUE;
        for (UmlObject object : elements) {
            minX = Math.min(minX, object.getX());
        }
        x = minX;
    }

    private void setY() {
        double minY = Double.MAX_VALUE;
        for (UmlObject object : elements) {
            minY = Math.min(minY, object.getY());
        }
        y = minY;
    }

    private void setWidth() {
        double maxX = Double.MIN_VALUE;
        for (UmlObject object : elements) {
            maxX = Math.max(maxX, object.getX() + object.getWidth());
        }

        width = maxX - x;
    }

    private void setHeight() {
        double maxY = Double.MIN_VALUE;
        for (UmlObject object : elements) {
            maxY = Math.max(maxY, object.getY() + object.getHeight());
        }

        height = maxY - y;
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (UmlObject object : elements) {
            object.draw(gc);
        }
        gc.setStroke(Color.PINK);
        gc.strokeRect(x, y, width, height);
    }

    @Override
    public void beSelected() {
        super.beSelected();
        for (UmlObject object : elements) {
            object.beSelected();
        }
    }

    @Override
    public void unSelected() {
        super.unSelected();
        for (UmlObject object : elements) {
            object.unSelected();
        }
    }

    @Override
    public boolean contain(Point2D pos) {
        for (UmlObject object : elements) {
            if (object.contain(pos)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UmlObject> ungroup() {
        return elements;
    }

    @Override
    public void movePosition(double dx, double dy) {
        super.movePosition(dx, dy);

        for (UmlObject object : elements) {
            object.movePosition(dx, dy);
        }
    }
}
