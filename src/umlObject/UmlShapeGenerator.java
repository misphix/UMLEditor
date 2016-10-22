package umlObject;

public class UmlShapeGenerator {
	
	public UmlShapeGenerator() {
		
	}
	
	public UmlShape getShape(UmlOperation type) {
		switch (type) {
		case CLASS:
			return new UmlClass();
		case USU_CASE:
			return new UmlUseCase();
		default:
			return null;
		}
	}
}
