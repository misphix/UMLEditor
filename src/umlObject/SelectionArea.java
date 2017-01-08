package umlObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SelectionArea {
    private double x, y, width, height;
    private boolean visible = false;

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

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(double width) {

        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {

        return visible;
    }

    public void draw(GraphicsContext gc) {
        if (visible) {
            gc.setFill(Color.DODGERBLUE);
            gc.setStroke(Color.DODGERBLUE);
            gc.setGlobalAlpha(0.35);
            gc.strokeRect(x, y, width, height);
            gc.fillRect(x, y, width, height);

            gc.setGlobalAlpha(1);
        }
    }
}
