package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class RitualController implements Initializable {

    @FXML
    private Label date;

    @FXML
    private VBox eveningContainer;

    @FXML
    private VBox middayContainer;

    @FXML
    private VBox morningContainer;

    @FXML
    private ImageView streak1;

    @FXML
    private ImageView streak2;

    @FXML
    private ImageView streak3;

    @FXML
    private ImageView streak4;

    @FXML
    private ImageView streak5;

    @FXML
    private ImageView streak6;

    @FXML
    private ImageView streak7;

    @FXML
    private Label streakBonus;

    @FXML
    private Label streakCount;

    @FXML
    private ProgressBar xpBar;

    @FXML
    private StackPane xpBarContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources){

        //set up shiny xp bar
        Rectangle shine = new Rectangle();
        shine.setHeight(12);

        shine.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#D9D9D900")),
                new Stop(0.5, Color.rgb(255, 255, 255, 1.0)),
                new Stop(1, Color.web("#73737300"))
        ));

        shine.widthProperty().bind(xpBar.widthProperty().multiply(xpBar.progressProperty()).subtract(10));

        xpBarContainer.getChildren().add(shine);

        StackPane.setMargin(shine, new Insets(12, 0, 0, 10));


    }

}

