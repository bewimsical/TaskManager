package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.viewmodels.RitualViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

    private final RitualViewModel vm = new RitualViewModel();


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

        date.textProperty().bind(vm.dateProperty());


    }


    @FXML
    void AddMorningRitual(MouseEvent event) {
        addRitual("Morning", event);
    }

    @FXML
    void addEveningRitual(MouseEvent event) {
        addRitual("Evening", event);
    }

    @FXML
    void addMiddayRitual(MouseEvent event) {
        addRitual("Midday", event);
    }

    private void addRitual(String timeOfDay, MouseEvent event){
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.initOwner(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("add-ritual-view.fxml"));
        Scene popUpScene = null;
        try {
            popUpScene = new Scene(fxmlLoader.load(), 500,574);
            popUpScene.getStylesheets().add(TaskManagerApplication.class.getResource("styles/style.css").toExternalForm());
            popUp.setScene(popUpScene);
            popUp.setTitle("Add Ritual");
            popUp.setX(primaryStage.getX() + ((primaryStage.getWidth() - 500)/2 ));
            popUp.setY(primaryStage.getY() + ((primaryStage.getHeight() - 574)/2 ));
            //popUp.show();
            AddRitualController arc = fxmlLoader.getController();
            arc.setTimeOfDay(timeOfDay);

            popUp.showAndWait();

            // After popup is closed, get the selected chore
            Chore selectedChore = arc.getSelectedChore();
            if (selectedChore != null){
                vm.createRitual(selectedChore, timeOfDay);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}

