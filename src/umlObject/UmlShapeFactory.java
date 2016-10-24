package umlObject;

public class UmlShapeFactory {
	
	public UmlShapeFactory() {
		
	}
	
	public UmlShape getShape(UmlOperation type) {
		switch (type) {
		case CLASS:
			return new UmlClass("Class");
		case USE_CASE:
			return new UmlUseCase("Use Case");
		default:
			return null;
		}
	}
}
