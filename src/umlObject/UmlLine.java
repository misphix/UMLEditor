package umlObject;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class UmlLine extends Group {
	private Line line = new Line();
	protected Shape shape;
	
	public UmlLine() {
		getChildren().add(line);
	}
	
	public void setStartPoint(Point2D start) {
		line.setStartX(start.getX());
		line.setStartY(start.getY());
	}
	
	public void setStartPoint(Rectangle start) {
		line.setStartX(start.getX());
		line.setStartY(start.getY());
	}
	
	public void setEndPoint(Point2D end) {
		line.setEndX(end.getX());
		line.setEndY(end.getY());
	}
	
	public Point2D getStartPoint() {
		return new Point2D(line.getStartX(), line.getStartY());
	}
	
	public Point2D getEndPoint() {
		return new Point2D(line.getEndX(), line.getEndY());
	}
	
	public void subscribeStartPoint(UmlShape shape, Rectangle port) {
		shape.moveFlag.addListener((obs, oldVal, newVal) -> {
			Point2D portScreenPoint = shape.localToScreen(port.getLayoutX(), port.getLayoutY());
			Point2D portCanvasPoint = getParent().screenToLocal(portScreenPoint);
			setStartPoint(portCanvasPoint);
			toFront();
		});
	}
	
	public void subscribeEndPoint(UmlShape shape, Rectangle port) {
		shape.moveFlag.addListener((obs, oldVal, newVal) -> {
			Point2D portScreenPoint = shape.localToScreen(port.getLayoutX(), port.getLayoutY());
			Point2D portCanvasPoint = getParent().screenToLocal(portScreenPoint);
			setEndPoint(portCanvasPoint);
			toFront();
		});
	}
	
	protected double getAngle() {
		Point2D start = getStartPoint(), end = getEndPoint();
	    double angle = Math.toDegrees(Math.atan2(end.getY() - start.getY(), end.getX() - start.getX()));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
}
