package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

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

    @FXML
    private StackPane xpBarContainer;

    private Label[] buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        buttons = new Label[]{allLabel, ritualsLabel, questsLabel, bossesLabel};

        //set up shiny xp bar
        Rectangle shine = new Rectangle();
        shine.setHeight(10);

        shine.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#D9D9D900")),
                new Stop(0.5, Color.rgb(255, 255, 255, 1.0)),
                new Stop(1, Color.web("#73737300"))
        ));

        shine.widthProperty().bind(xpBar.widthProperty().multiply(xpBar.progressProperty()).subtract(6));

        xpBarContainer.getChildren().add(shine);

        StackPane.setMargin(shine, new Insets(8, 0, 0, 7));


    }
    @FXML
    void showAll(MouseEvent event) {
        setActiveButton(allLabel);
        double current = xpBar.getProgress();
        double next = Math.min(current + 0.1, 1.0); // cap at 1.0
        xpBar.setProgress(next);
    }

    @FXML
    void showBosses(MouseEvent event) {
        setActiveButton(bossesLabel);
        double current = xpBar.getProgress();
        double next = Math.max(current - 0.1, 0); // cap at 1.0
        xpBar.setProgress(next);
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

