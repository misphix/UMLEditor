package umlObject;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class UmlUseCase extends UmlShape {


    public UmlUseCase(double x, double y) {
        width = 100;
        height = 50;

        name = "Use Case";
        this.x = x - width / 2;
        this.y = y - height / 2;

        portsInitialize();
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawUseCase(gc);
        for (Port port : ports) {
            port.draw(gc);
        }
    }

    private void drawUseCase(GraphicsContext gc) {
        gc.setFill(Color.AQUAMARINE);
        gc.fillOval(x, y, width, height);
        gc.strokeOval(x, y, width, height);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.strokeText(name, x + width / 2, y + height / 2);
    }
}
