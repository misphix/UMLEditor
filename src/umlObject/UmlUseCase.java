package umlObject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

public class UmlUseCase extends UmlShape {
	@FXML
	private Text useCaseName;
	private final String styleClass = "uml-use-case";
	
	public UmlUseCase() {
		super();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UmlUseCase.fxml"));
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
		useCaseName.setText(name);
	}
	
	@Override
	public void setPosition(double x, double y) {
		double width = 60;
		double height = 30;
		super.setPosition(x - width, y - height);
	}
	
	@Override
	protected void setStyleClass() {
		getStyleClass().add(styleClass);
	}
}
