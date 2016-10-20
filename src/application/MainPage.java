package application;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

public class MainPage {
	public void test(ActionEvent event) {
		System.out.println(((ToggleButton)event.getSource()).isPressed());
	}
}
