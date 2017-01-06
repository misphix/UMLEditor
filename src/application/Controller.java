package application;

import controlButton.ControlButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import umlMode.*;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class Controller implements Initializable {
    private UmlMode noneMode = new NoneMode();
    private UmlMode mode = noneMode;
    private Set<ControlButton> buttons = new HashSet<>();
    private final ToggleGroup BUTTON_GROUP = new ToggleGroup();
    @FXML
    private VBox vBox;

    public Controller() {
        ControlButton selectBtn = new ControlButton("Select", new SelectionMode());
        selectBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(selectBtn);
        ControlButton associationBtn = new ControlButton("Association", new AssociationMode());
        associationBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(associationBtn);
        ControlButton generalizationBtn = new ControlButton("Generalization", new GeneralizationMode());
        generalizationBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(generalizationBtn);
        ControlButton compositionBtn = new ControlButton("Composition", new CompositionMode());
        compositionBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(compositionBtn);
        ControlButton classBtn = new ControlButton("Class", new ClassMode());
        classBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(classBtn);
        ControlButton useCaseBtn = new ControlButton("Use Case", new UseCaseMode());
        useCaseBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(useCaseBtn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBox.getChildren().addAll(buttons);
        BUTTON_GROUP.selectedToggleProperty().addListener((event) -> {
            for (ControlButton btn : buttons) {
                if (btn.isSelected()) {
                    mode = btn.getMode();
                    return;
                }
            }
            mode = noneMode;
        });
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        mode.mousePressEvent(event);
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        mode.mouseDraggedEvent(event);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        mode.mouseReleasedEvent(event);
    }
}
