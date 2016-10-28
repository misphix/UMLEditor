package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class UmlGeneralizationLine extends UmlLine {
	public UmlGeneralizationLine() {
		super();
		shape = new Polygon(0, 0, 10, 0, 5, 10);
		getChildren().add(shape);
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
		shape.setRotate(getAngle()+90);
	}
}
