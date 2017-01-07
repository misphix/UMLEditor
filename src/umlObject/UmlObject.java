package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class UmlObject {
    String name;
    double x, y;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void ungroup() {

    }

    public double getY() {
        return y;
    }

    public double getX() {

        return x;
    }

    public void movePosition(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public abstract void draw(GraphicsContext gc);
    public abstract void beSelected();
    public abstract void unSelected();
    public abstract boolean isSelected();
    public abstract boolean contain(Point2D pos);
    public abstract boolean contain(Point2D start, Point2D end);
}
