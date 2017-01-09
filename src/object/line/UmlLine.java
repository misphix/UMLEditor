package object.line;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import object.Constants;
import object.Port;
import object.UmlObject;

public class UmlLine extends UmlObject {
    Port start;
    private Port end;

    public UmlLine(Port start, Port end) {
        assert start != null : "Port's start shouldn't be null";
        assert end != null : "Port's end shouldn't be null";

        this.start = start;
        this.end = end;
    }

    public void setEnd(Port end) {
        this.end = end;
    }

    double getAngle() {
        double angle = Math.toDegrees(Math.atan2(end.getY() - start.getY(), end.getX() - start.getX()));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);

        double startX = start.getX() + Constants.PORT_WIDTH / 2;
        double startY = start.getY() + Constants.PORT_WIDTH / 2;
        double endX = end.getX() + Constants.PORT_WIDTH / 2;
        double endY = end.getY() + Constants.PORT_WIDTH / 2;
        gc.strokeLine(startX, startY, endX, endY);
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
