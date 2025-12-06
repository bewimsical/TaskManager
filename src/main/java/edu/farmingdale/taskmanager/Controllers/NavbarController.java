package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavbarController implements Initializable {

    @FXML
    private Label level;

    @FXML
    private Rectangle navBorder;

    @FXML
    private Label navBosses;

    @FXML
    private StackPane navContainer;

    @FXML
    private Label navHome;

    @FXML
    private HBox navLogoContainer;

    @FXML
    private Label navLogout;

    @FXML
    private Label navParty;

    @FXML
    private Label navQuests;

    @FXML
    private Label navRituals;

    @FXML
    private Label navSettings;

    @FXML
    private VBox navUserInfo;

    @FXML
    private VBox navVbox;

    @FXML
    private Label usernameLabel;

    boolean expanded;
    Label[] labels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = Session.getInstance();
        usernameLabel.setText(session.getUser().getUsername());
        level.setText(String.valueOf(session.getUser().getLevel()));
        labels = new Label[] {navHome, navParty, navRituals,navQuests, navBosses, navSettings, navLogout};
        expanded = false;
        //toggleMenu();

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

    @FXML
    void toggleMenu() {
        double targetContainerWidth = expanded ? 102 : 300;
        double targetWidth = expanded ? 92 : 290;

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(250),
                        new KeyValue(navContainer.prefWidthProperty(), targetContainerWidth),
                        new KeyValue(navContainer.maxWidthProperty(), targetContainerWidth),
                        new KeyValue(navLogoContainer.prefWidthProperty(), targetWidth),
                        new KeyValue(navLogoContainer.maxWidthProperty(), targetWidth),
                        new KeyValue(navVbox.prefWidthProperty(), targetWidth),
                        new KeyValue(navVbox.maxWidthProperty(), targetWidth),
                        new KeyValue(navBorder.widthProperty(), targetContainerWidth)

                )
        );

        timeline.play();

        if (!expanded) {
            // We are expanding → show labels AFTER animation finishes
            timeline.setOnFinished(e -> {
                for (Label label : labels) {
                    label.setVisible(true);
                }
                navUserInfo.setVisible(true);
                navUserInfo.setManaged(true);
//                navLogoContainer.setPrefWidth(290);
//                navVbox.setPrefWidth(290);
                navBorder.setWidth(300);
            });
        } else {
            // We are collapsing → hide labels immediately
            for (Label label : labels) {
                label.setVisible(false);
            }
            navUserInfo.setVisible(false);
            navUserInfo.setManaged(false);
//            navLogoContainer.setPrefWidth(92);
//            navVbox.setPrefWidth(92);
            navBorder.setWidth(102);
        }

        expanded = !expanded;
    }

    @FXML
    void goBosses(MouseEvent event) {
        switchScene(event.getSource(), "boss-view.fxml");
    }

    @FXML
    void goHome(MouseEvent event) {
        switchScene(event.getSource(), "profile-view.fxml");
    }

    @FXML
    void goParty(MouseEvent event) {
        switchScene(event.getSource(), "party-view.fxml");
    }

    @FXML
    void goQuests(MouseEvent event) {
        switchScene(event.getSource(), "quest-view.fxml");
    }

    @FXML
    void goRituals(MouseEvent event) {
        switchScene(event.getSource(), "ritual-view.fxml");
    }

    @FXML
    void goSettings(MouseEvent event) {
        switchScene(event.getSource(), "settings-view.fxml");

    }

    @FXML
    void logout(MouseEvent event) {
        Session.getInstance().clearSession();
        switchScene(event.getSource(),"login-view.fxml");
    }
    @FXML
    private void handleSettingsClick() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/farmingdale/taskmanager/views/settings-view.fxml"));
        Parent settingsRoot = loader.load();


    }



}

