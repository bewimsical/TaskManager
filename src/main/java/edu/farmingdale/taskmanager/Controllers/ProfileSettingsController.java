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

        if (currentUser == null) return;
        String newUsername = usernameField.getText();
        String newEmail = emailField.getText();
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);

        //Referencing Firestore
        TaskManagerApplication.fstore.collection("users").document("user1").update("username", newUsername, "email", newEmail)
                .addListener(() -> {
                    statusLabel.setText("Profile updated successfully!");
                    System.out.println("Profile updated!");
                }, Runnable::run);

        //update session
        Session.getInstance().getUser().setUsername(newUsername);
        Session.getInstance().getUser().setEmail(newEmail);

            System.out.println("Saving profile:");
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);

            statusLabel.setText("Saved!");
        }
    }


