package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PartyPopupController implements Initializable {

    @FXML
    private Label AllFriends;

    @FXML
    private Rectangle allianceBtn;

    @FXML
    private Label allianceLabel;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    }


}

