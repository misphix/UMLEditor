package umlObject;

public class UmlUseCase extends UmlShape {
	private final String styleClass = "uml-use-case";
	
	public UmlUseCase() {
		super();
		setStyleClass();
	}
	
	@Override
	protected void setStyleClass() {
		getStyleClass().add(styleClass);
	}
}
