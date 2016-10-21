package application;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class MainPage {
	@FXML private ToggleGroup UMLElement;
	
	public void test(MouseEvent e) {
		if (UMLElement.getSelectedToggle() instanceof ToggleButton) {
			ToggleButton tb = (ToggleButton) UMLElement.getSelectedToggle();
			System.out.println(tb.getText());
		}
		System.out.println(e.getX() + " " + e.getY());
	}
}
