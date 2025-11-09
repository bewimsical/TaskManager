package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestController implements Initializable {

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
    private VBox mainContainer;

    @FXML
    private VBox miniBossesContainer1;

    @FXML
    private FlowPane objectivesContainer;

    @FXML
    private ImageView questImageView;

    @FXML
    private VBox trackedContainer;

    private Label[] buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttons = new Label[]{activeButton, completeButton, failedButton};
    }

    @FXML
    void createCustomQuest(MouseEvent event) {

    }

    @FXML
    void toggleActive(MouseEvent e) {
        for(Label label:buttons){
            label.getStyleClass().remove("active");
        }
        Label button = (Label) e.getSource();
        button.getStyleClass().add("active");
    }

}
