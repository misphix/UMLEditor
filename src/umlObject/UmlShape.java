package umlObject;

import javafx.scene.Group;

public abstract class UmlShape extends Group {
	protected String name;

	
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
	}
	
	public void selected() {
		this.toFront();
	}

	public void unSelected() {
		this.getStyleClass().clear();
		this.setStyleClass();
	}

	public void setStartPosition(double x, double y) {
			
	}
	
	protected abstract void setStyleClass();
}
