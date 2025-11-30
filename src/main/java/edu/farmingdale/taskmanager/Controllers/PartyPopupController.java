package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PartyPopupController implements Initializable {

    @FXML
    private Rectangle allianceBtn;

    @FXML
    private Label allianceLabel;

    @FXML
    private Rectangle cancelBtn;

    @FXML
    private Label cancelLabel;

    @FXML
    private Rectangle createBtn;

    @FXML
    private Label createLabel;

    @FXML
    private Rectangle guildBtn;

    @FXML
    private Label guildLabel;

    @FXML
    private VBox searchContainer;

    @FXML
    private TextField searchField;

    @FXML
    private Text typeDescription;

    @FXML
    private StackPane popupRoot;

    private Pane parent;

    private String type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setParentContainer(Pane parent) {
        this.parent = parent;
    }

    @FXML
    void cancel(MouseEvent event) {

        // remove popup
        parent.getChildren().remove(popupRoot);

        // remove overlay (it’s now the last child)
        parent.getChildren().remove(parent.getChildren().size() - 1);
    }

    @FXML
    void createParty(MouseEvent event) {


        // remove popup
        parent.getChildren().remove(popupRoot);

        // remove overlay (it’s now the last child)
        parent.getChildren().remove(parent.getChildren().size() - 1);
    }

    @FXML
    void displayFriends(MouseEvent event) {

    }

    @FXML
    void search(MouseEvent event) {

    }

    @FXML
    void showFriends(MouseEvent event) {

    }

    @FXML
    void toggleSelected(MouseEvent event) {
        String guild = "A guild is for members in the same household. All chores are shared between guild members.";
        String alliance = "An alliance is for members in separate households. Chores are not shared between alliance members";

        allianceLabel.getStyleClass().remove("active");
        guildLabel.getStyleClass().remove("active");

        Label label = (Label) event.getSource();
        type = label.getText();

        if (type.equals("Guild")){
            typeDescription.setText(guild);
        } else {
            typeDescription.setText(alliance);
        }

        label.getStyleClass().add("active");
    }


}

