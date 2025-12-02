package edu.farmingdale.taskmanager.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

    // FXML elements linked via fx:id in the .fxml file
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Button signupButton;

    @FXML
    private Button goBackButton;

    @FXML
    private StackPane contentArea;

    private final FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onSignupButtonClick() {

    }

    public void onGoBackButtonClick() {

    }

}
