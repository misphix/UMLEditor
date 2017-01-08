package object;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Port {
    private final BooleanBinding visible;
    private double x, y;

    public Port(double x, double y) {
        this.x = x;
        this.y = y;
        visible = null;
    }

    public Port(double x,double y, BooleanProperty visibleBind) {
        this.x = x;
        this.y = y;
        visible = visibleBind.and(visibleBind);
    }

    public void movePosition(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void draw(GraphicsContext gc) {
        if (visible.get()) {
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, Constants.PORT_WIDTH, Constants.PORT_WIDTH);
        }
    }

    public double distance(Point2D target) {
        Point2D myPoint = new Point2D(x, y);

        return myPoint.distance(target);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
