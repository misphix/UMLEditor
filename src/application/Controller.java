package application;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import uiObject.ControlButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import umlMode.*;
import umlObject.SelectionArea;
import umlObject.UmlGroup;
import umlObject.UmlObject;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    private final List<UmlObject> elements = new ArrayList<>();
    private UmlMode noneMode = new NoneMode(elements);
    private UmlMode mode = noneMode;
    private List<ControlButton> buttons = new ArrayList<>();
    private final ToggleGroup BUTTON_GROUP = new ToggleGroup();
    private final SelectionArea selectionArea = new SelectionArea();
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
        drawer.start();
        canvas.getBoundsInParent();
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
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                for (UmlObject obj : elements) {
                    obj.draw(canvas.getGraphicsContext2D());
                }
                selectionArea.draw(canvas.getGraphicsContext2D());
            }
        };
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        mode.mousePressEvent(event, selectionArea);
    }

    @FXML
    private void mouseDragged(MouseEvent event) {
        mode.mouseDraggedEvent(event, selectionArea);
    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        mode.mouseReleasedEvent(event, selectionArea);
    }

    @FXML
    private void group() {
        List<UmlObject> selectedObjects = getSelectedObjects();
        if (selectedObjects.size() > 1) {
            UmlGroup group = new UmlGroup(selectedObjects);
            elements.removeAll(selectedObjects);
            elements.add(group);
        } else {
            alertBox("Group Warning", "You have to select at lease two UML object");
        }
    }

    private List<UmlObject> getSelectedObjects() {
        List<UmlObject> selectedObject = new ArrayList<>();

        for (UmlObject object : elements) {
            if (object.isSelected()) {
                selectedObject.add(object);
            }
        }

        return selectedObject;
    }

    @FXML
    private void ungroup() {
        try {
            ungroupElement();
        } catch (NullPointerException e) {
            alertBox("Ungroup Warning", e.getMessage());
        }
    }

    private void ungroupElement() throws NullPointerException {
        List<UmlObject> selectedObjects = getSelectedObjects();
        if (selectedObjects.size() == 1) {
            UmlObject group = selectedObjects.get(0);
            List<UmlObject> groupElements = group.ungroup();
            if (groupElements == null) {
                throw new NullPointerException("It isn't a group");
            }

            elements.remove(group);
            elements.addAll(groupElements);
        } else {
            alertBox("Ungroup Warning", "You have to select just one UML object");
        }
    }

    private void alertBox(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.show();
    }
}
