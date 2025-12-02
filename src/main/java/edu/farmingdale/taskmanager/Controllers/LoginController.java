package edu.farmingdale.taskmanager.Controllers;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import edu.farmingdale.taskmanager.FirestoreClient;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{



    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button googleButton;

    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public boolean loginOnClick(MouseEvent event) {
        UserRecord userRecord;
        try {
            userRecord = TaskManagerApplication.fauth.getUserByEmail(emailTextField.getText());
            System.out.println("Successfully fetched user data: " + userRecord.getEmail());
            User user = userRepo.getUserById(userRecord.getUid());

            String pw = user.getPassword();
            if (pw.equals(passwordTextField.getText())) {
                FirestoreClient.getUser("users", user.getId());
                switchScene(event.getSource(), "profile-view.fxml");
                return true;
            }else {
                System.out.println("passwords do not match");
                return false;
            }
        } catch (FirebaseAuthException | ResourceNotFoundException e) {
            System.out.println("Error logging user in the firebase");
            return false;
        }
    }


    private static void switchScene(Object source, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(TaskManagerApplication.class.getResource(fxmlFile));
            Pane wrapper = TaskManagerApplication.setup(root);
            Stage stage = (Stage)((Node)source).getScene().getWindow();
            double oldWidth = ((Node)source).getScene().getWidth();
            double oldHeight = ((Node)source).getScene().getHeight();
            Scene scene = new Scene(wrapper, oldWidth, oldHeight);
            scene.getStylesheets().add(TaskManagerApplication.class.getResource("styles/style.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
