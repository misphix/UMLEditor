package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SelectedArea extends Rectangle {
	public SelectedArea() {
		super();
		setVisible(false);
		setOpacity(0.35);
		setFill(Paint.valueOf("DODGERBLUE"));
		setStroke(Paint.valueOf("BLACK"));
	}
	
	public void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
		setTranslateX(0);
		setTranslateY(0);
	}
	
	public void draw(Point2D start, Point2D end) {
		double dx = end.getX() - start.getX();
		double dy = end.getY() - start.getY();
		setWidth(Math.abs(dx));
		setHeight(Math.abs(dy));
		
		if (dx < 0) {
			setTranslateX(dx);
		}
		if (dy < 0) {
			setTranslateY(dy);
		}
	}
}
