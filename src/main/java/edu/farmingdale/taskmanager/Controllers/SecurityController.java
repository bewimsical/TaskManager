package edu.farmingdale.taskmanager.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SecurityController {

    public AnchorPane securityContentArea;
    public Label statusLabel;
    public Accordion securityAccordion;

    //PASSWORD FIELDS
    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmPasswordField;

    //EMAIL FIELDS
    public TextField currentEmail;     // USER TYPES current email
    public TextField newEmailField;    // USER TYPES new email

    public CheckBox twoFactorToggle;
    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = Session.getInstance().getUser();

        if (currentUser == null) {
            showStatus("Error: No logged-in user found", true);
            return;
        }
        loadCurrentEmail();

    }


    private void loadCurrentEmail() {
        try {
            UserRecord record = FirebaseAuth.getInstance().getUser(currentUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Failed to load current email", true);
        }
    }

    // CHANGE PASSWORD
    @FXML
    public void updatePassword(ActionEvent event) {

        if (currentUser == null) return;

        String newPass = newPasswordField.getText();
        String confirm = confirmPasswordField.getText();

        if (newPass.isEmpty() || confirm.isEmpty()) {
            showStatus("Please fill all password fields.", true);
            return;
        }

        if (!newPass.equals(confirm)) {
            showStatus("Passwords do not match.", true);
            return;
        }

        try {
            // Update Firestore password
            TaskManagerApplication.fstore
                    .collection("users")
                    .document(currentUser.getId())
                    .update("password", newPass)
                    .addListener(() -> showStatus("Password updated!", false), Runnable::run);

            // Update session object
            currentUser.setPassword(newPass);
            Session.getInstance().getUser().setPassword(newPass);

            // Clear UI fields
            newPasswordField.clear();
            confirmPasswordField.clear();
            currentPasswordField.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Error updating password.", true);
        }
    }

    //CHANGE EMAIL
    @FXML
    public void updateEmail(ActionEvent actionEvent) {

        if (currentUser == null) return;

        String enteredCurrentEmail = currentEmail.getText();
        String newEmail = newEmailField.getText();
        String actualCurrentEmail = currentUser.getEmail();

        // Validate current email
        if (enteredCurrentEmail.isEmpty()) {
            showStatus("Please enter your current email.", true);
            return;
        }

        if (!enteredCurrentEmail.equals(actualCurrentEmail)) {
            showStatus("Current email does not match our records.", true);
            return;
        }

        // Validate new email
        if (newEmail.isEmpty()) {
            showStatus("Please enter a new email.", true);
            return;
        }

        if (newEmail.equals(actualCurrentEmail)) {
            showStatus("New email cannot be the same as your current email.", true);
            return;
        }

        try {
            // Update Firestore
            TaskManagerApplication.fstore
                    .collection("users")
                    .document(currentUser.getId())
                    .update("email", newEmail)
                    .addListener(() -> showStatus("Email updated!", false), Runnable::run);

            // Update session
            currentUser.setEmail(newEmail);
            Session.getInstance().getUser().setEmail(newEmail);

            // Clear fields
            newEmailField.clear();
            currentEmail.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Error updating email.", true);
        }
    }

    // SIGN OUT EVERYWHERE (add later)
    @FXML
    public void signOutEverywhere(ActionEvent actionEvent) { }

    // 2FA TOGGLE (will add later)
    @FXML
    public void toggleTwoFactor(ActionEvent actionEvent) { }

    // STATUS MESSAGE
    private void showStatus(String msg, boolean error) {
        statusLabel.setText(msg);
        statusLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}








    