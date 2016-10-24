package application;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import umlObject.UmlOperation;
import umlObject.UmlShape;
import umlObject.UmlShapeFactory;

public class Controller implements Initializable {
	@FXML
	private ToggleGroup umlElement;
	@FXML
	private Pane canvas;
	private Map<String, UmlOperation> operation;
	private UmlShapeFactory shapeGen;
	private UmlShape selectedShape = null;
	private Point2D mousePressed;
	private UmlOperation type = null;

	public Controller() {
		operation = new HashMap<String, UmlOperation>();
		operation.put("Select", UmlOperation.SELECT);
		operation.put("Association", UmlOperation.ASSOCIATION);
		operation.put("Generalization", UmlOperation.GENERALIZATION);
		operation.put("Composition", UmlOperation.COMPOSITION);
		operation.put("Class", UmlOperation.CLASS);
		operation.put("Use Case", UmlOperation.USE_CASE);
		shapeGen = new UmlShapeFactory();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		umlElement.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
			try {
				Toggle selectType = umlElement.getSelectedToggle();
				ToggleButton btn = (ToggleButton) selectType;
				type = operation.get(btn.getText());
			} catch (NullPointerException event) {
				return;
			}
		});
	}
	
	@FXML
	private void canvasPressedListener(MouseEvent e) {
		try {
			switch (type) {
			case SELECT:
				selectPressedHandler(e);
				break;
			case CLASS:
			case USE_CASE:
				UmlShape shape = shapeGen.getShape(type);
				shape.setPosition(e.getX(), e.getY());
				canvas.getChildren().add(shape);
				break;
			default:
				break;
			}
		} catch (NullPointerException event) {
			return;
		}
	}
	
	@FXML
	private void canvasDragedListener(MouseEvent e) {
		double dx = e.getX() - mousePressed.getX();
		double dy = e.getY() - mousePressed.getY();
		mousePressed = new Point2D(e.getX(), e.getY());
		
		try {
			switch (type) {
			case SELECT:
				selectedShape.relocate(selectedShape.getLayoutX() + dx, selectedShape.getLayoutY() + dy);
				break;
			default:
				break;
			}
		} catch (NullPointerException event) {
			return;
		}
		
		
	}

	private void selectPressedHandler(MouseEvent e) {
		ObservableList<Node> nodes = canvas.getChildren();
		mousePressed = new Point2D(e.getX(), e.getY());
		unSelectAll();
		for (int i = nodes.size() - 1; i >= 0; --i) {
			Node node = nodes.get(i);
			
			if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
				try {
					UmlShape shape = (UmlShape) node;
					selectedShape = shape;
					shape.selected();
				} catch (NullPointerException event) {
					return;
				}
				break;
			}
		}
	}
	
	private void unSelectAll() {
		ObservableList<Node> nodes = canvas.getChildren();
		
		for (int i = nodes.size() - 1; i >= 0; --i) {
			Node node = nodes.get(i);
			
			try {
				UmlShape shape = (UmlShape) node;
				shape.unSelected();
				selectedShape = null;
			} catch (NullPointerException event) {
				return;
			}
		}
	}
}
