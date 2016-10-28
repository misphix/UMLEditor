package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class UmlCompositionLine extends UmlLine {
	public UmlCompositionLine() {
		super();
		shape = new Rectangle(10, 10);
		getChildren().add(shape);
	}
	
	@Override
	public void setStartPoint(Point2D start) {
		super.setStartPoint(start);
		setShapePosition();
	}
	
	@Override
	public void setEndPoint(Point2D end) {
		super.setEndPoint(end);
		setShapePosition();
	}
	
	private void setShapePosition() {
		Point2D start = getStartPoint();
		shape.setLayoutX(start.getX() - 5);
		shape.setLayoutY(start.getY() - 5);
		shape.setRotate(getAngle()+45);
	}

}
