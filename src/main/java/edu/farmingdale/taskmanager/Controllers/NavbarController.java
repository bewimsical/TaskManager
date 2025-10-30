package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
            Parent root = FXMLLoader.load(NavbarController.class.getResource(fxmlFile));
            Stage stage = (Stage)((Node)source).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(NavbarController.class.getResource("styles/style.css").toExternalForm());
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

    }

    @FXML
    void goHome(MouseEvent event) {
        switchScene(event.getSource(), "hello-view.fxml");
    }

    @FXML
    void goParty(MouseEvent event) {

    }

    @FXML
    void goQuests(MouseEvent event) {

    }

    @FXML
    void goRituals(MouseEvent event) {

    }

    @FXML
    void goSettings(MouseEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {

    }

}

