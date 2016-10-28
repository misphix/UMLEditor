package umlObject;

import java.util.List;

import javafx.geometry.Point2D;

public class UmlGroup extends UmlShape {
	private final String styleClass = "uml-group";
	private final String selectedStyleClass = "uml-shape-selected";

	public UmlGroup() {
		super("Group");
		setStyleClass();
		getStylesheets().add(getClass().getResource("UmlShape.css").toExternalForm());
	}
	
	public void add(List<UmlShape> selectedShapes) {
		double x = Double.MAX_VALUE, y = Double.MAX_VALUE;
		for (UmlShape shape : selectedShapes) {
			x = x < shape.getLayoutX() ? x : shape.getLayoutX();
			y = y < shape.getLayoutY() ? y : shape.getLayoutY();
		}
		for (UmlShape shape : selectedShapes) {
			shape.setLayoutX(shape.getLayoutX() - x);
			shape.setLayoutY(shape.getLayoutY() - y);
			shape.unSelected();
			getChildren().add(shape);
		}
		setLayoutX(x);
		setLayoutY(y);
	}

	@Override
	public void selected() {
		super.selected();
		this.getStyleClass().clear();
		this.getStyleClass().add(selectedStyleClass);
	}
	
	@Override
	public Point2D getPort(Point2D clickPoint) {
		return null;
	}
	
	@Override
	protected void setStyleClass() {
		getStyleClass().add(styleClass);
	}

}
