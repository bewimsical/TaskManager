package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class NavbarController {

    @FXML
    private Label level;

    @FXML
    private Rectangle navBorder;

    @FXML
    private Label navBosses;

    @FXML
    private StackPane navContainer;

    @FXML
    private Label navHome;

    @FXML
    private HBox navLogoContainer;

    @FXML
    private Label navLogout;

    @FXML
    private Label navParty;

    @FXML
    private Label navQuests;

    @FXML
    private Label navRituals;

    @FXML
    private Label navSettings;

    @FXML
    private VBox navUserInfo;

    @FXML
    private VBox navVbox;

    private static void switchScene(Object source, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(TaskManagerApplication.class.getResource(fxmlFile));
            Pane wrapper = TaskManagerApplication.setup(root);
            Stage stage = (Stage)((Node)source).getScene().getWindow();
            double oldWidth = ((Node)source).getScene().getWidth();
            double oldHeight = ((Node)source).getScene().getHeight();
            Scene scene = new Scene(wrapper, oldWidth, oldHeight);
            scene.getStylesheets().add(TaskManagerApplication.class.getResource("styles/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toggleMenu(MouseEvent event) {

    }

    @FXML
    void goBosses(MouseEvent event) {
        switchScene(event.getSource(), "boss-view.fxml");
    }

    @FXML
    void goHome(MouseEvent event) {
        switchScene(event.getSource(), "profile-view.fxml");
    }

    @FXML
    void goParty(MouseEvent event) {
        switchScene(event.getSource(), "party-view.fxml");
    }

    @FXML
    void goQuests(MouseEvent event) {
        switchScene(event.getSource(), "quest-view.fxml");
    }

    @FXML
    void goRituals(MouseEvent event) {
        switchScene(event.getSource(), "ritual-view.fxml");
    }

    @FXML
    void goSettings(MouseEvent event) {
        switchScene(event.getSource(), "settings-view.fxml");

    }

    @FXML
    void logout(MouseEvent event) {

    }
    @FXML
    private void handleSettingsClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/farmingdale/taskmanager/views/settings-view.fxml"));
        Parent settingsRoot = loader.load();


    }


}

