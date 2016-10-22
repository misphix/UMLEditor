package umlObject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class UmlClass extends UmlShape {
	@FXML
	private Text className;
	private final String styleClass = "uml-class";
	
	public UmlClass() {
		super();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UmlClass.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setStyleClass();
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		className.setText(name);
	}
	
	@Override
	public void setPosition(double x, double y) {
		double width = 100 / 2;
		double height = 130 / 2;
		super.setPosition(x - width, y - height);
	}
	
	@Override
	protected void setStyleClass() {
		getStyleClass().add(styleClass);
	}
}
