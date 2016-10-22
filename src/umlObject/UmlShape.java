package umlObject;

import javafx.scene.layout.Pane;

public abstract class UmlShape extends Pane {
	protected String name;
	
	public UmlShape() {
		super();
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
	
	protected abstract void setStyleClass();
}
