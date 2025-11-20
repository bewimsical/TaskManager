package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ProfileSettingsController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label statusLabel;

    private User currentUser;

    @FXML
    public void initialize() {
        // Loading user from session
        currentUser = Session.getInstance().getUser();

        if (currentUser != null) {
            usernameField.setText(currentUser.getUsername());
            emailField.setText(currentUser.getEmail());
        }
    }

        @FXML
        private void saveProfile() {
            String username = usernameField.getText();
            String email = emailField.getText();

            System.out.println("Saving profile:");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);

            statusLabel.setText("Saved!");
        }
    }


