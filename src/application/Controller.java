package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import umlObject.SelectedArea;
import umlObject.UmlGroup;
import umlObject.UmlLine;
import umlObject.UmlLineFactory;
import umlObject.UmlOperation;
import umlObject.UmlShape;
import umlObject.UmlShapeFactory;

public class Controller implements Initializable {
	@FXML
	private MenuItem property, group, ungroup;
	@FXML
	private ToggleGroup umlElement;
	@FXML
	private Pane canvas;
	private Map<String, UmlOperation> operation;
	private UmlShapeFactory shapeGen;
	private UmlLineFactory lineGen;
	private List<UmlShape> selectedShapes = new ArrayList<UmlShape>();
	private Point2D mousePressed;
	private UmlOperation type = null;
	private SelectedArea selectedArea = new SelectedArea();
	private UmlLine drawingLine = null;

	public Controller() {
		operation = new HashMap<String, UmlOperation>();
		operation.put("Select", UmlOperation.SELECT);
		operation.put("Association", UmlOperation.ASSOCIATION);
		operation.put("Generalization", UmlOperation.GENERALIZATION);
		operation.put("Composition", UmlOperation.COMPOSITION);
		operation.put("Class", UmlOperation.CLASS);
		operation.put("Use Case", UmlOperation.USE_CASE);
		shapeGen = new UmlShapeFactory();
		lineGen = new UmlLineFactory();
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
	private void clickedGroupHandler() {
		UmlGroup group = new UmlGroup();
		group.add(selectedShapes);
		canvas.getChildren().removeAll(selectedShapes);
		canvas.getChildren().add(group);
	}
	
	@FXML
	private void clickedUngroupHandler() {
		UmlGroup group = (UmlGroup) selectedShapes.get(0);
		double posX = group.getLayoutX(), posY = group.getLayoutY();
		canvas.getChildren().remove(group);
		for (Node node : group.getChildren()) {
			node.setLayoutX(node.getLayoutX() + posX);
			node.setLayoutY(node.getLayoutY() + posY);
		}
		canvas.getChildren().addAll(group.getChildren());
		group.getChildren().clear();
	}
	
	@FXML
	private void clickedPropertiesHandler() {
		try {
			UmlShape shape = selectedShapes.get(0);
			TextInputDialog dialog = new TextInputDialog(shape.getName());
			dialog.setTitle("Property");
			dialog.setHeaderText(null);
			dialog.setContentText("Name:");
	
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> shape.setName(name));
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}
	
	@FXML
	private void canvasPressedListener(MouseEvent e) {
		try {
			switch (type) {
			case SELECT:
				selectPressedHandler(e);
				break;
			case ASSOCIATION:
			case GENERALIZATION:
			case COMPOSITION:
				drawLinePressHandler(e);
				break;
			case CLASS:
			case USE_CASE:
				UmlShape shape = shapeGen.getShape(type);
				shape.setStartPosition(e.getX(), e.getY());
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
			
			if (node.isPressed()) {
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
	
	private void drawLinePressHandler(MouseEvent e) {
		ObservableList<Node> nodes = canvas.getChildren();
		mousePressed = new Point2D(e.getX(), e.getY());

		for (int i = nodes.size() - 1; i >= 0; --i) {
			Node node = nodes.get(i);
			
			if (node.isPressed()) {
				try {
					UmlShape shape = (UmlShape) node;
					Point2D start = shape.getPort(mousePressed);
					drawingLine = lineGen.getLine(type);
					drawingLine.setStartPoint(start);
					drawingLine.setEndPoint(start);
					canvas.getChildren().add(drawingLine);
				} catch (NullPointerException event) {
					return;
				} catch (ClassCastException event) {
					continue;
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
				property.setDisable(true);
				group.setDisable(true);
				ungroup.setDisable(true);
			} catch (NullPointerException event) {
				return;
			} catch (ClassCastException e) {
				continue;
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
			case ASSOCIATION:
			case GENERALIZATION:
			case COMPOSITION:
				drawingLine.setEndPoint(new Point2D(e.getX(), e.getY()));
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
			selectedShape.setPosition(selectedShape.getLayoutX() + dx, selectedShape.getLayoutY() + dy);
		}
	}

	private void DrawSelectedArea(MouseEvent e) {
		Point2D end = new Point2D(e.getX(), e.getY());
		
		selectedArea.setVisible(true);
		selectedArea.draw(mousePressed, end);
	}
	
	@FXML
	private void canvasMouseReleasedListener(MouseEvent e) {
		try {
			switch(type) {
			case SELECT:
				selectModeRelease();
				break;
			case ASSOCIATION:
			case GENERALIZATION:
			case COMPOSITION:
				drawLineRelease(e);
				break;
			case CLASS:
			case USE_CASE:
			default:
				break;
			}
		} catch (NullPointerException except) {
			
		}
	}
	
	private void selectModeRelease() {
		shapeSelectedAreaContain();
		selectedArea.setVisible(false);
		if (selectedShapes.size() == 1) {
			if (selectedShapes.get(0) instanceof UmlGroup) {
				ungroup.setDisable(false);
			} else {
				property.setDisable(false);
			}
		} else if (selectedShapes.size() > 1) {
			group.setDisable(false);
		}
	}
	
	private void shapeSelectedAreaContain() {
		if (selectedArea.isVisible()) {
			List<Node> nodes = canvas.getChildren().stream().filter(e -> e instanceof UmlShape).collect(Collectors.toList());
			List<Node> shapes = nodes.stream().filter(e -> selectedArea.getBoundsInParent().contains(e.getBoundsInParent())).collect(Collectors.toList());
			
			for (Node node : shapes) {
				UmlShape shape = (UmlShape) node;
				shape.selected();
				selectedShapes.add(shape);
			}
		}
	}
	
	private void drawLineRelease(MouseEvent e) {
		ObservableList<Node> nodes = canvas.getChildren();
		Point2D mouseRelease = new Point2D(e.getX(), e.getY());

		for (int i = nodes.size() - 1; i >= 0; --i) {
			Node node = nodes.get(i);
			
			if (node.getBoundsInParent().contains(mouseRelease)) {
				try {
					UmlShape shape = (UmlShape) node;
					Point2D end = shape.getPort(mouseRelease);
					drawingLine.setEndPoint(end);
				} catch (NullPointerException event) {
					return;
				} catch (ClassCastException event) {
					continue;
				}
				drawingLine = null;
				return;
			}
		}
		
		canvas.getChildren().remove(drawingLine);
		drawingLine = null;
	}
}
