package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddRitualController implements Initializable {

    @FXML
    private ComboBox<Chore> choreSelector;

    @FXML
    private VBox morningContainer;

    @FXML
    private Label timeOfDay;

    @FXML
    private Button createRitualButton;

    Chore selectedChore;

    List<Chore> chores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chores = Session.getInstance().getChores();
        choreSelector.getItems().addAll(chores);

        // Helper method to create a properly styled ListCell
        Callback<ListView<Chore>, ListCell<Chore>> cellFactory = cb -> createChoreCell();

        choreSelector.setCellFactory(cellFactory);
        choreSelector.setButtonCell(createChoreCell());
    }

    /**
     * Creates a ListCell for Chore with max width, ellipsis, and tooltip.
     */
    private ListCell<Chore> createChoreCell() {
        return new ListCell<>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Chore chore, boolean empty) {
                label.getStyleClass().add("ritual-item-label");
                super.updateItem(chore, empty);

                if (empty || chore == null) {
                    setGraphic(null);
                    setTooltip(null);
                } else {
                    label.setText(chore.getName());
                    label.setMaxWidth(300);               // limit width
                    label.setEllipsisString("...");       // truncate long text

                    // Tooltip if text is too long
                    if (chore.getName().length() > 30) { // adjust threshold as needed
                        Tooltip tooltip = new Tooltip(chore.getName());
                        setTooltip(tooltip);
                    } else {
                        setTooltip(null);
                    }

                    setGraphic(label);
                }
            }
        };
    }

    public void setTimeOfDay(String time){
        timeOfDay.setText(time);
    }

    public Chore getSelectedChore(){
        return selectedChore;
    }


    @FXML
    void createRitual(MouseEvent event) {
        Stage stage = (Stage) createRitualButton.getScene().getWindow();
        selectedChore = choreSelector.getValue();
        stage.close();
    }


}

