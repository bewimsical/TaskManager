package edu.farmingdale.taskmanager.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SecurityController {

    @FXML public AnchorPane securityContentArea;
    @FXML public Label statusLabel;

    // PASSWORD FIELDS
    @FXML public PasswordField currentPasswordField;
    @FXML public PasswordField newPasswordField;
    @FXML public PasswordField confirmPasswordField;

    // EMAIL FIELDS
    @FXML public TextField currentEmail;
    @FXML public TextField newEmailField;

    // 2FA
    @FXML public CheckBox twoFactorToggle;

    private User currentUser;

    @FXML
    public void initialize() {

        currentUser = Session.getInstance().getUser();

        if (currentUser.getAge()<18){
            currentUser.setParentalControls(true);
        }
        if (currentUser.isParentalControls()){
            disableSecurityFields();
        }
        if (currentUser.getAge()>=18){
            currentUser.setParentalControls(false);

            //update firestore
            TaskManagerApplication.fstore
                    .collection("users")
                    .document(currentUser.getId())
                    .update("parentalControls", false);

        }

        if (currentUser == null) {
            showStatus("Error: No logged-in user found", true);
            return;
        }

        // Load email
        loadCurrentEmail();

        // Disable UI AFTER FXML loads (important!)
        Platform.runLater(() -> {
            if (currentUser.isParentalControls()) {
                disableSecurityFields();
            }
        });
    }

    private void disableSecurityFields() {

        // Disable password fields
        if (currentPasswordField != null) currentPasswordField.setDisable(true);
        if (newPasswordField != null) newPasswordField.setDisable(true);
        if (confirmPasswordField != null) confirmPasswordField.setDisable(true);

        // Disable email fields
        if (currentEmail != null) currentEmail.setDisable(true);
        if (newEmailField != null) newEmailField.setDisable(true);

        // Disable 2FA toggle
        if (twoFactorToggle != null) twoFactorToggle.setDisable(true);

        // Show status
        statusLabel.setText("Parental Controls Enabled — Editing Disabled");
        statusLabel.setStyle("-fx-text-fill: #c0392b;");
    }

    private void loadCurrentEmail() {
        try {
            FirebaseAuth.getInstance().getUser(currentUser.getId());
        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Failed to load current email.", true);
        }
    }

    @FXML
    public void updatePassword(ActionEvent event) {

        if (currentUser.isParentalControls()) {
            showStatus("Password changes are disabled under Parental Controls.", true);
            return;
        }

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
            TaskManagerApplication.fstore
                    .collection("users")
                    .document(currentUser.getId())
                    .update("password", newPass)
                    .addListener(() -> showStatus("Password updated!", false), Runnable::run);

            currentUser.setPassword(newPass);
            Session.getInstance().getUser().setPassword(newPass);

            newPasswordField.clear();
            confirmPasswordField.clear();
            currentPasswordField.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Error updating password.", true);
        }
    }

    @FXML
    public void updateEmail(ActionEvent actionEvent) {

        if (currentUser.isParentalControls()) {
            showStatus("Email changes are disabled under Parental Controls.", true);
            return;
        }

        if (currentUser == null) return;

        String enteredCurrentEmail = currentEmail.getText();
        String newEmail = newEmailField.getText();
        String actualCurrentEmail = currentUser.getEmail();

        if (enteredCurrentEmail.isEmpty()) {
            showStatus("Please enter your current email.", true);
            return;
        }

        if (!enteredCurrentEmail.equals(actualCurrentEmail)) {
            showStatus("Current email does not match our records.", true);
            return;
        }

        if (newEmail.isEmpty()) {
            showStatus("Please enter a new email.", true);
            return;
        }

        if (newEmail.equals(actualCurrentEmail)) {
            showStatus("New email cannot be the same as your current email.", true);
            return;
        }

        try {
            TaskManagerApplication.fstore
                    .collection("users")
                    .document(currentUser.getId())
                    .update("email", newEmail)
                    .addListener(() -> showStatus("Email updated!", false), Runnable::run);

            currentUser.setEmail(newEmail);
            Session.getInstance().getUser().setEmail(newEmail);

            newEmailField.clear();
            currentEmail.clear();

        } catch (Exception e) {
            e.printStackTrace();
            showStatus("Error updating email.", true);
        }
    }
    @FXML public void signOutEverywhere(ActionEvent actionEvent) { }
    @FXML public void toggleTwoFactor(ActionEvent actionEvent) { }

    private void showStatus(String msg, boolean error) {
        statusLabel.setText(msg);
        statusLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: #50280d;");
    }
}








    