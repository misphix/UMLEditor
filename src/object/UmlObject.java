package object;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class UmlObject {
    protected String name;
    protected double x, y, width, height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {

        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void movePosition(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public List<UmlObject> ungroup() {
        return null;
    }

    public Port getClosestPort(Point2D target) {
        return null;
    }

    public abstract void draw(GraphicsContext gc);

    public abstract void beSelected();

    public abstract void unSelected();

    public abstract boolean isSelected();

    public abstract boolean contain(Point2D pos);
}
