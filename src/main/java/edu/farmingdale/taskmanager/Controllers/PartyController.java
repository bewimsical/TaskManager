package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Party;
import edu.farmingdale.taskmanager.cards.LargeFriendCard;
import edu.farmingdale.taskmanager.cards.LargePartyCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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


    @Override
    public void initialize(URL location, ResourceBundle resources){
        User testUser = new User();

        testUser.setUsername("TestPlayer");
        testUser.setLevel(5);
        testUser.setStreak(3);
        testUser.setVanquishedBossCount(2);
        testUser.setCompletedQuestCount(4);
        testUser.setProfileUrl("wizard_cat.PNG");

        LargeFriendCard card = new LargeFriendCard(testUser);
        LargeFriendCard card2 = new LargeFriendCard(testUser);

        friendContainer.getChildren().addAll(card.createView(), card2.createView());

        Party party1 = new Party("Party", "guild", "2", "2", "2");

        LargePartyCard pc = new LargePartyCard(party1);

        partyContainer.getChildren().addAll(pc.createView());

    }


    @FXML
    void displayFriends(MouseEvent event) {

    }

}
