package edu.farmingdale.taskmanager.Controllers;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.DocumentReference;
import com.google.api.core.ApiFuture;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class ParentalControlsController {

    @FXML
    private CheckBox parentalToggle;

    @FXML
    private Label ageLabel;

    @FXML
    private Label adultLabel;

    @FXML
    private Label statusLabel;

    private User currentUser;

    @FXML
    public void initialize() {

        currentUser = Session.getInstance().getUser();

        // Display age + status
        ageLabel.setText(String.valueOf(currentUser.getAge()));
        adultLabel.setText(currentUser.isAdult() ? "Adult" : "Minor");

        // Apply age-based rules BEFORE loading Firestore
        applyAgeRules();

        // Load stored parental control value
        loadParentalControlState();
    }

    private void applyAgeRules() {

        // Child: 12 and under → always ON, locked
        if (currentUser.getAge() <= 12) {
            parentalToggle.setSelected(true);
            parentalToggle.setDisable(true);
            statusLabel.setText("Parental Controls are required for users under 13.");
        }

        // Teen: 13–17 → toggle allowed
        else if (currentUser.getAge() < 18) {
            parentalToggle.setDisable(false);
            statusLabel.setText("Recommended: Enable Parental Controls.");
        }

        // Adult: 18+
        else {
            parentalToggle.setDisable(false);
            statusLabel.setText("");
        }
    }

    private void loadParentalControlState() {

        DocumentReference docRef = TaskManagerApplication.fstore
                .collection("users").document(currentUser.getId());

        ApiFuture<DocumentSnapshot> future = docRef.get();

        future.addListener(() -> {
            try {
                DocumentSnapshot document = future.get();

                if (document.exists() && document.contains("parentalControls")) {

                    boolean enabled = Boolean.TRUE.equals(document.getBoolean("parentalControls"));

                    // Apply Firestore value only for users allowed to toggle
                    if (!parentalToggle.isDisabled()) {
                        parentalToggle.setSelected(enabled);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }


    /** WHEN USER TOGGLES THE BUTTON */
    @FXML
    public void toggleParentalControls() {

        // If toggle is disabled → ignore attempt
        if (parentalToggle.isDisabled()) {
            statusLabel.setText("This setting cannot be changed for your age.");
            return;
        }

        boolean enabled = parentalToggle.isSelected();

        DocumentReference docRef = TaskManagerApplication.fstore
                .collection("users").document(currentUser.getId());

        docRef.update("parentalControls", enabled)
                .addListener(() -> statusLabel.setText(
                        enabled ? "Parental Controls Enabled" : "Parental Controls Disabled"
                ), Runnable::run);
    }
}
