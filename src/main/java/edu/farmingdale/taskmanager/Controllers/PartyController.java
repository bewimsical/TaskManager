package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.cards.*;
import edu.farmingdale.taskmanager.viewmodels.PartyViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PartyController implements Initializable {

    @FXML
    private VBox friendContainer;

    @FXML
    private VBox partyContainer;

    @FXML
    private VBox searchContainer;

    @FXML
    private ImageView addParty;

    @FXML
    private TextField searchField;

    @FXML
    private Label AllFriends;

    @FXML
    private Label partyName;

    private final PartyViewModel vm = new PartyViewModel();


    @Override
    public void initialize(URL location, ResourceBundle resources){
//        User testUser = new User();
//
//        testUser.setUsername("TestPlayer");
//        testUser.setLevel(5);
//        testUser.setStreak(3);
//        testUser.setVanquishedBossCount(2);
//        testUser.setCompletedQuestCount(4);
//        testUser.setProfileUrl("wizard_cat.PNG");
//
//        LargeFriendCard card = new LargeFriendCard(testUser);
//        LargeFriendCard card2 = new LargeFriendCard(testUser);
//
//        friendContainer.getChildren().addAll(card.createView(), card2.createView());

//        Party party1 = new Party("Party", "Guild", "2", "2", "2");
//
//        LargePartyCard pc = new LargePartyCard(party1);

        //partyContainer.getChildren().addAll(pc.createView());

        partyName.textProperty().bind(vm.nameProperty());
        setUpPartyList(vm.getVisibleParties(), partyContainer);
        setUpUserList(vm.getVisibleUsers(), friendContainer);

        vm.setUpView();

    }


    @FXML
    void displayFriends(MouseEvent event) {

    }

    public void AddParty(MouseEvent mouseEvent) {
    }

    public void addFriend(SearchCard sc){
        User friend = sc.getData();
        if (friend != null) {
            vm.addFriend(friend);
        }
    }

    @FXML
    void search(MouseEvent event) {
        String username = searchField.getText();
        User user = vm.search(username);
        if (user != null){
            SearchCard sc = new SearchCard(user, this::addFriend);
            searchContainer.getChildren().add(sc.createView());
        }
    }

    private void setUpUserList(ObservableList<User> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<User>) change -> {
            container.getChildren().clear();
            //TODO ?????
            //vm.setCurrentBossCard(null);
            for (User u : list) {
                LargeFriendCard card = new LargeFriendCard(u);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void setUpPartyList(ObservableList<Party> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<Party>) change -> {
            container.getChildren().clear();
            //TODO ?????
            //vm.setCurrentBossCard(null);
            for (Party p : list) {
                LargePartyCard card = new LargePartyCard(p, this::handlePartyClick);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void handlePartyClick(LargePartyCard card){
        Party party = card.getData();
        vm.showParty(party);
    }

    @FXML
    void showFriends(MouseEvent event) {
        vm.showFriends();
    }




}
