package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class SettingsController {

    public Button logoutBtn;
    public Button parentalBtn;
    public Button gameBtn;
    public Button deactivateBtn;
    public Button securityBtn;
    public Button profileBtn;

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
        loadPage("profilesetting-view.fxml");
    }

    @FXML
    private void openGameSettings() {
        loadPage("game-view.fxml");
    }

    @FXML
    private void openSecurity() {
        loadPage("security-view.fxml");
    }

    @FXML
    private void openParental() {
        loadPage("parental-view.fxml");
    }

    @FXML
    private void logout() { loadPage("login-view.fxml");
        System.out.println("Logging out...");

    }


    @FXML
    public void goBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/edu/farmingdale/taskmanager/profile-view.fxml")
            );

            Node root = loader.load();
            ((Node) event.getSource()).getScene().setRoot((javafx.scene.Parent) root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}


