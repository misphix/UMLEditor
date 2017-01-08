package umlObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public abstract class UmlShape extends UmlObject {
    List<Port> ports = new ArrayList<>();
    private BooleanProperty selected = new SimpleBooleanProperty(false);

    @Override
    public void beSelected() {
        selected.set(true);
    }

    @Override
    public void unSelected() {
        selected.set(false);
    }

    @Override
    public boolean isSelected() {
        return selected.get();
    }

    @Override
    public void movePosition(double dx, double dy) {
        super.movePosition(dx, dy);
        for (Port port : ports) {
            port.movePosition(dx, dy);
        }
    }

    @Override
    public boolean contain(Point2D pos) {
        return (pos.getX() > x && pos.getX() < x + width && pos.getY() > y && pos.getY() < y + height);
    }

    void portsInitialize() {
        Port up = new Port(x + width / 2 - Constants.PORT_WIDTH / 2, y - Constants.PORT_WIDTH / 2, selected);
        Port left = new Port(x - Constants.PORT_WIDTH / 2, y + height / 2 - Constants.PORT_WIDTH / 2, selected);
        Port right = new Port(x + width - Constants.PORT_WIDTH / 2, y + height / 2 - Constants.PORT_WIDTH / 2, selected);
        Port down = new Port(x + width / 2 - Constants.PORT_WIDTH / 2, y + height - Constants.PORT_WIDTH / 2, selected);
        ports.add(up);
        ports.add(left);
        ports.add(right);
        ports.add(down);
    }
}
