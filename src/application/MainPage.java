package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class MainPage {
	@FXML private ToggleGroup UMLElement;
	
	public void test(ActionEvent e) {
		ToggleButton tb = (ToggleButton) UMLElement.getSelectedToggle();
		System.out.println(tb.getText());
	}
}
