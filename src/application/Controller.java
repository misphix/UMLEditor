package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
import umlObject.SelectedArea;
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
	private List<UmlShape> selectedShapes = new ArrayList<UmlShape>();
	private Point2D mousePressed;
	private UmlOperation type = null;
	private SelectedArea selectedArea = new SelectedArea();

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
		canvas.getChildren().add(selectedArea);
		umlElement.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
			try {
				Toggle selectType = umlElement.getSelectedToggle();
				ToggleButton btn = (ToggleButton) selectType;
				type = operation.get(btn.getText());
			} catch (NullPointerException event) {
				type = null;
			}
			if (type != UmlOperation.SELECT) {
				unSelectAll();
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
	
	private void selectPressedHandler(MouseEvent e) {
		ObservableList<Node> nodes = canvas.getChildren();
		mousePressed = new Point2D(e.getX(), e.getY());
		selectedArea.setPosition(e.getX(), e.getY());
		unSelectAll();
		for (int i = nodes.size() - 1; i >= 0; --i) {
			Node node = nodes.get(i);
			
			if (node.getBoundsInParent().contains(e.getX(), e.getY())) {
				try {
					UmlShape shape = (UmlShape) node;
					selectedShapes.add(shape);
					shape.selected();
				} catch (NullPointerException event) {
					return;
				} catch (ClassCastException event) {
					
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
				selectedShapes.clear();
			} catch (NullPointerException event) {
				return;
			} catch (ClassCastException e) {
				
			}
		}
	}
	
	@FXML
	private void canvasDragedListener(MouseEvent e) {
		try {
			switch (type) {
			case SELECT:
				if (selectedShapes.size() != 0) {
					moveSelectedShape(e);
				} else {
					DrawSelectedArea(e);
				}
				break;
			default:
				break;
			}
		} catch (NullPointerException event) {
			return;
		}
	}
	
	private void moveSelectedShape(MouseEvent e) {
		double dx = e.getX() - mousePressed.getX();
		double dy = e.getY() - mousePressed.getY();
		mousePressed = new Point2D(e.getX(), e.getY());
		for (UmlShape selectedShape : selectedShapes) {
			selectedShape.relocate(selectedShape.getLayoutX() + dx, selectedShape.getLayoutY() + dy);
		}
	}

	private void DrawSelectedArea(MouseEvent e) {
		Point2D end = new Point2D(e.getX(), e.getY());
		
		selectedArea.setVisible(true);
		selectedArea.draw(mousePressed, end);
	}
	
	@FXML
	private void canvasMouseReleasedListener(MouseEvent e) {
		shapeSelectedAreaContain();
		selectedArea.setVisible(false);
	}
	
	private void shapeSelectedAreaContain() {
		if (selectedArea.isVisible()) {
			List<Node> nodes = canvas.getChildren().stream().filter(e -> e instanceof UmlShape).collect(Collectors.toList());
			List<Node> shapes = nodes.stream().filter(e -> selectedArea.getLayoutBounds().contains(e.getLayoutBounds())).collect(Collectors.toList());
			
			for (Node node : shapes) {
				UmlShape shape = (UmlShape) node;
				shape.selected();
				selectedShapes.add(shape);
			}
		}
	}
}
