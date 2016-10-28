package umlObject;

public class UmlLineFactory {
	public UmlLineFactory() {
			
		}
	
	public UmlLine getLine(UmlOperation type) {
		switch (type) {
		case ASSOCIATION:
			return new UmlAssociationLine();
		case GENERALIZATION:
			return new UmlGeneralizationLine();
		case COMPOSITION:
			return new UmlCompositionLine();
		default:
			return null;
		}
	}
}
