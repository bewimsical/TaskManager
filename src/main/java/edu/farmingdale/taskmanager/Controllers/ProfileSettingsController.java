package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ProfileSettingsController {

        @FXML private TextField usernameField;
        @FXML private TextField emailField;
        @FXML private Label statusLabel;

        @FXML
        public void initialize() {
            // Load saved values (temporary example)
            usernameField.setText("PlayerOne");
            emailField.setText("player@example.com");
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


