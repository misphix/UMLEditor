package application;

import javafx.animation.AnimationTimer;
import uiObject.ControlButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import umlMode.*;
import umlObject.UmlObject;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private final List<UmlObject> elements = new ArrayList<>();
    private UmlMode noneMode = new NoneMode(elements);
    private UmlMode mode = noneMode;
    private List<ControlButton> buttons = new ArrayList<>();
    private final ToggleGroup BUTTON_GROUP = new ToggleGroup();
    private AnimationTimer drawer;
    @FXML
    private VBox vBox;
    @FXML
    private Canvas canvas;

    public Controller() {
        ControlButton selectBtn = new ControlButton("Select", new SelectionMode(elements));
        selectBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(selectBtn);
        ControlButton associationBtn = new ControlButton("Association", new AssociationMode(elements));
        associationBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(associationBtn);
        ControlButton generalizationBtn = new ControlButton("Generalization", new GeneralizationMode(elements));
        generalizationBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(generalizationBtn);
        ControlButton compositionBtn = new ControlButton("Composition", new CompositionMode(elements));
        compositionBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(compositionBtn);
        ControlButton classBtn = new ControlButton("Class", new ClassMode(elements));
        classBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(classBtn);
        ControlButton useCaseBtn = new ControlButton("Use Case", new UseCaseMode(elements));
        useCaseBtn.setToggleGroup(BUTTON_GROUP);
        buttons.add(useCaseBtn);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButtons();
        setDrawer();
    }

    private void addButtons() {
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

    private void setDrawer() {
        drawer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (UmlObject obj : elements) {
                    obj.draw(canvas.getGraphicsContext2D());
                }
            }
        };
        drawer.start();
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
