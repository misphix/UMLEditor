package umlObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;

public class UmlGeneralizationLine extends UmlLine {
    private final double width = 8, height = 10;

    public UmlGeneralizationLine(Port start, Port end) {
        super(start, end);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);

        gc.save();
        double startX = start.getX() + Constants.PORT_WIDTH / 2;
        double startY = start.getY() + Constants.PORT_WIDTH / 2;
        double[] arrX = {startX, startX - width / 2, startX + width / 2};
        double[] arrY = {startY, startY + height, startY + height};
        Rotate r = new Rotate(getAngle() - 90, startX, startY);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
        gc.strokePolygon(arrX, arrY, 3);
        gc.fillPolygon(arrX, arrY, 3);
        gc.restore();
    }
}
