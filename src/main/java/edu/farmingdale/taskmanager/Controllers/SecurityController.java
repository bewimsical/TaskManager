package edu.farmingdale.taskmanager.Controllers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
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

    public PasswordField currentPasswordField;
    public PasswordField newPasswordField;
    public PasswordField confirmPasswordField;
    public Label currentEmailLabel;
    public TextField newEmailField;
    public CheckBox twoFactorToggle;
    private User currentUser;

    @FXML
    public void initialize() {
        currentUser = Session.getInstance().getUser();
        if (currentUser == null) {

        }
        loadCurrentEmail();
    }

    private void loadCurrentEmail() {

        try {
            UserRecord record = FirebaseAuth.getInstance().getUser(currentUser.getId());
            currentEmailLabel.setText(record.getEmail());
        } catch (Exception e){
            e.printStackTrace();

        }
    }


        public void updatePassword (ActionEvent actionEvent){


        }

        public void updateEmail (ActionEvent actionEvent){
        }

        public void signOutEverywhere (ActionEvent actionEvent){
        }

        public void toggleTwoFactor (ActionEvent actionEvent){

        }
        private void showStatus(String msg, boolean error){
        statusLabel.setText(msg);

        }


    }

    