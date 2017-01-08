package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class UmlLine extends UmlObject {
    private Port start, end;

    public UmlLine(Port start, Port end) {
        assert start != null : "Port's start shouldn't be null";
        assert end != null : "Port's end shouldn't be null";

        this.start = start;
        this.end = end;
    }

    public void setEnd(Port end) {
        this.end = end;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeLine(start.getX() + Constants.PORT_WIDTH / 2, start.getY() + Constants.PORT_WIDTH / 2, end.getX() + Constants.PORT_WIDTH / 2, end.getY() + Constants.PORT_WIDTH / 2);
    }

    @Override
    public void beSelected() {

    }

    @Override
    public void unSelected() {

    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean contain(Point2D pos) {
        return false;
    }
}
