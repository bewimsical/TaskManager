package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class ProfileController {

    @FXML
    private Rectangle allButton;

    @FXML
    private Label allLabel;

    @FXML
    private StackPane bossesButton;

    @FXML
    private Label bossesLabel;

    @FXML
    private Label currentXp;

    @FXML
    private Label levelLabel;

    @FXML
    private Label levelXp;

    @FXML
    private ImageView profilePic;

    @FXML
    private StackPane questsButton;

    @FXML
    private Label questsLabel;

    @FXML
    private Label ritualsLabel;

    @FXML
    private Label username;

    @FXML
    private ProgressBar xpBar;

    private Label[] buttons = {allLabel, ritualsLabel, questsLabel, bossesLabel};

    @FXML
    void showAll(MouseEvent event) {
        setActiveButton(allLabel);
    }

    @FXML
    void showBosses(MouseEvent event) {
        setActiveButton(bossesLabel);
    }

    @FXML
    void showQuests(MouseEvent event) {
        setActiveButton(questsLabel);
    }

    @FXML
    void showRituals(MouseEvent event) {
        setActiveButton(ritualsLabel);
    }

    private void setActiveButton(Label button){
        for(Label label:buttons){
            label.getStyleClass().remove("active");
        }

        button.getStyleClass().add("active");
    }

}

