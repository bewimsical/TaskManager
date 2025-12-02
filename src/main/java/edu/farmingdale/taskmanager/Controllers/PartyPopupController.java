package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.cards.ExtraSmallFriendCard;
import edu.farmingdale.taskmanager.cards.LargeFriendCard;
import edu.farmingdale.taskmanager.cards.MediumFriendCard;
import edu.farmingdale.taskmanager.cards.SmallFriendCard;
import edu.farmingdale.taskmanager.viewmodels.PartyViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
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
    private FlowPane memberContainer;

    @FXML
    private StackPane popupRoot;

    private Pane parent;

    private String type;
    private String name;
    private List<String> members;


    private final ObservableList<User> visibleUsers = FXCollections.observableArrayList();
    private final ObservableList<User> visibleMembers = FXCollections.observableArrayList();

    private final PartyViewModel vm = new PartyViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setUpUserList(visibleUsers, searchContainer);
        setUpMemberList(visibleMembers, memberContainer);
        visibleUsers.addAll(vm.getFriends());


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
        //only accept if name is filled in.
        //send info to vm to create a party and display it

        // remove popup
        parent.getChildren().remove(popupRoot);

        // remove overlay (it’s now the last child)
        parent.getChildren().remove(parent.getChildren().size() - 1);
    }

    @FXML
    void displayFriends(MouseEvent event) {
        //delete this
    }

    @FXML
    void search(MouseEvent event) {
        //this fill filter friends based on entered text
    }

    @FXML
    void showFriends(MouseEvent event) {
        //delete this
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

    private void setUpUserList(ObservableList<User> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<User>) change -> {
            container.getChildren().clear();
            //TODO ?????
            //vm.setCurrentBossCard(null);
            for (User u : list) {
                MediumFriendCard card = new MediumFriendCard(u, this::selectFriend);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void setUpMemberList(ObservableList<User> list, FlowPane container){
        // reactively render lists:
        list.addListener((ListChangeListener<User>) change -> {
            container.getChildren().clear();
            //TODO ?????
            //vm.setCurrentBossCard(null);
            for (User u : list) {
                ExtraSmallFriendCard card = new ExtraSmallFriendCard(u);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void selectFriend(MediumFriendCard card){
        User friend = card.getData();
        if (!visibleMembers.contains(friend)) {
            visibleMembers.add(friend);
        }
    }


}

