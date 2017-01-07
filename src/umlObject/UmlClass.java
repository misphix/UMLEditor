package umlObject;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class UmlClass extends UmlShape {
    private final double CLASS_NAME_HEIGHT = 20, PROPERTY_HEIGHT = 30, METHOD_HEIGHT = 30;

    public UmlClass(double x, double y) {
        width = 100;
        height = CLASS_NAME_HEIGHT + PROPERTY_HEIGHT + METHOD_HEIGHT;
        name = "Class";
        this.x = x - width / 2;
        this.y = y - height / 2;

        portsInitialize();
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawClassDiagram(gc);
        for (Port port : ports) {
            port.draw(gc);
        }
    }

    private void drawClassDiagram(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillRect(x, y, width, CLASS_NAME_HEIGHT); // draw class name field
        gc.strokeRect(x, y, width, CLASS_NAME_HEIGHT);
        gc.fillRect(x, y + CLASS_NAME_HEIGHT, width, PROPERTY_HEIGHT); // draw class property field
        gc.strokeRect(x, y + CLASS_NAME_HEIGHT, width, PROPERTY_HEIGHT);
        gc.fillRect(x, y + CLASS_NAME_HEIGHT + METHOD_HEIGHT, width, METHOD_HEIGHT); //draw class method field
        gc.strokeRect(x, y + CLASS_NAME_HEIGHT + METHOD_HEIGHT, width, METHOD_HEIGHT);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeText(name, x + width / 2, y + CLASS_NAME_HEIGHT / 2);
    }
}
