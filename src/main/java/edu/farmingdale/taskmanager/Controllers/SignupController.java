package edu.farmingdale.taskmanager.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseBossRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseChoreRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseQuestRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;
import edu.farmingdale.taskmanager.factories.BossFactory;
import edu.farmingdale.taskmanager.factories.QuestFactory;
import edu.farmingdale.taskmanager.factories.UserFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class SignupController implements Initializable {

    // FXML elements linked via fx:id in the .fxml file
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Button signupButton;

    @FXML
    private Button goBackButton;

    @FXML
    private StackPane contentArea;

    private final FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @FXML
    private TextField phoneNumberTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean onSignupButtonClick() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(emailTextField.getText())
                .setEmailVerified(false)
                .setPassword(passwordTextField.getText())
                .setPhoneNumber("+" + phoneNumberTextField.getText())
                .setDisplayName(emailTextField.getText())
                .setDisabled(false);

        UserRecord userRecord;
        try {
            userRecord = TaskManagerApplication.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");

            String Id = userRecord.getUid();
            String day = "MONDAY";
            String profilePic = "wizard_cat.PNG";
            int age = 18;
            signUp(Id, usernameTextField.getText(),passwordTextField.getText(), emailTextField.getText(), age,profilePic, day);

            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error creating a new user in the firebase");
            return false;
        }
    }

    public void onGoBackButtonClick(MouseEvent event) {
        switchScene(event.getSource(),"login-view.fxml");
    }


    public static void signUp(String userId, String username, String password, String email, int age, String profileImage, String weekStart){
        //This would be set up in the sign up viewModel
        FirebaseUserRepository userRepository = new FirebaseUserRepository();
        FirebaseChoreRepository choreRepository = new FirebaseChoreRepository();
        FirebaseQuestRepository questRepository = new FirebaseQuestRepository();
        FirebaseBossRepository bossRepository = new FirebaseBossRepository();

        //create a user
        User user = UserFactory.generate(userId, username,password,email,age,profileImage, weekStart);
        userRepository.setUser(user);

        Session session = Session.getInstance();
        session.setRituals(new HashMap<>());
        session.setBosses(new HashMap<>());
        session.setQuests(new HashMap<>());
        session.setAssignedChoreIds(new HashSet<>());

        //generate user chores
        try {
            System.out.println("Creating New User's Chores");
            List<Chore> chores = choreRepository.getDefaultChores();
            for (Chore chore: chores){
                String uuid = UUID.randomUUID().toString();
                chore.setId(uuid);
                choreRepository.setChore(chore, user);
            }
            Session.getInstance().setChores(chores);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());;
        }
        //generate bosses
        System.out.println("Creating New User's Bosses");
        for (int i = 0; i < 4; i++) {
            Boss b = BossFactory.generate();
            bossRepository.setBoss(b, user);
            System.out.println("Boss Created!");
        }
        //generate quests
        System.out.println("Creating New User's Quests");
        for (int i = 0; i < 4; i++) {
            Quest q = QuestFactory.generate();
            questRepository.setQuest(q, user);
            System.out.println("Creating New User's Quest Created");
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
