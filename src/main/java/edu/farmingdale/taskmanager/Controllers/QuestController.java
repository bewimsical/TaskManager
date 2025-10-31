package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class QuestController {

    @FXML
    private Label activeButton;

    @FXML
    private Label bossName;

    @FXML
    private Label completeButton;

    @FXML
    private ImageView customBossCard;

    @FXML
    private Label failedButton;

    @FXML
    private Rectangle mainContainer;

    @FXML
    private VBox miniBossesContainer1;

    @FXML
    private FlowPane objectivesContainer;

    @FXML
    private ImageView questImageView;

    @FXML
    private VBox trackedContainer;

    @FXML
    void createCustomQuest(MouseEvent event) {

    }

    @FXML
    void toggleActive(MouseEvent event) {

    }

}

