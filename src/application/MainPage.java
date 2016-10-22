package application;

import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import umlObject.UmlOperation;
import umlObject.UmlShape;
import umlObject.UmlShapeGenerator;

public class MainPage {
	@FXML
	private ToggleGroup umlElement;
	@FXML
	private Pane canvas;
	private Map<String, UmlOperation> operation;
	private UmlShapeGenerator shapeGen;

	public MainPage() {
		operation = new HashMap<String, UmlOperation>();
		operation.put("Select", UmlOperation.SELECT);
		operation.put("Association", UmlOperation.ASSOCIATION);
		operation.put("Generalization", UmlOperation.GENERALIZATION);
		operation.put("Composition", UmlOperation.COMPOSITION);
		operation.put("Class", UmlOperation.CLASS);
		operation.put("Use Case", UmlOperation.USU_CASE);
		shapeGen = new UmlShapeGenerator();
	}

	public void test(MouseEvent e) {
		Toggle selectType = umlElement.getSelectedToggle();
		if (selectType instanceof ToggleButton) {
			ToggleButton btn = (ToggleButton) selectType;
			UmlOperation type = operation.get(btn.getText());
			try {
				UmlShape shape = shapeGen.getShape(type);
				shape.setPosition(e.getX(), e.getY());
				canvas.getChildren().add(shape);
			} catch (NullPointerException ev) {
				
			}
		}
	}
}
