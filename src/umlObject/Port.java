package umlObject;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Port {
    private final BooleanBinding visible;
    private double x, y;

    Port(double x,double y, BooleanProperty visibleBind) {
        this.x = x;
        this.y = y;
        visible = visibleBind.and(visibleBind);
    }

    void movePosition(double dx, double dy) {
        x += dx;
        y += dy;
    }

    void draw(GraphicsContext gc) {
        if (visible.get()) {
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, Constants.PORT_WIDTH, Constants.PORT_WIDTH);
        }
    }
}
