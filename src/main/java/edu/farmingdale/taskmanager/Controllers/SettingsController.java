package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class SettingsController {

    @FXML
    private StackPane contentArea;

    private void loadPage(String fxmlFile) {
        try {
            Node node = FXMLLoader.load(getClass().getResource("/edu/farmingdale/taskmanager/" + fxmlFile));
            contentArea.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openProfile() {
        loadPage("profile-view.fxml");
    }

    @FXML
    private void openGameSettings() {
        loadPage("game-view.fxml");
    }

    @FXML
    private void openSoundSettings() {
        loadPage("sound-view.fxml");
    }

    @FXML
    private void openSecurity() {
        loadPage("security-view.fxml");
    }

    @FXML
    private void openDeactivate() {
        loadPage("deactivate-view.fxml");
    }

    @FXML
    private void openParental() {
        loadPage("parental-view.fxml");
    }

    @FXML
    private void logout() {
        System.out.println("Logging out...");

    }
}
