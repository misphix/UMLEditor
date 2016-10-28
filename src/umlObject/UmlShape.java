package umlObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public abstract class UmlShape extends Group {
	protected String name;
	public BooleanProperty moveFlag = new SimpleBooleanProperty();
	
	public UmlShape(String name) {
		super();
		this.name = name;

	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPosition(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
		toggleMoveFlag();
	}
	
	public void selected() {
		this.toFront();
	}

	public void unSelected() {
		this.getStyleClass().clear();
		this.setStyleClass();
	}

	protected void toggleMoveFlag() {
		moveFlag.set(!moveFlag.get());
	}
	
	public abstract void setStartPosition(double x, double y);
	public abstract Rectangle getPort(Point2D clickPoint);
	protected abstract void setStyleClass();
}
