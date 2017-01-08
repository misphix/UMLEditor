package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class UmlObject {
    String name;
    double x, y, width, height;
    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void ungroup() {

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

    public abstract void draw(GraphicsContext gc);

    public abstract void beSelected();

    public abstract void unSelected();

    public abstract boolean isSelected();

    public abstract boolean contain(Point2D pos);
}
