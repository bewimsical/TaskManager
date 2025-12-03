package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.cards.RitualStatCard;
import edu.farmingdale.taskmanager.cards.SmallFriendCard;
import edu.farmingdale.taskmanager.cards.SmallPartyCard;
import edu.farmingdale.taskmanager.viewmodels.ProfileViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Rectangle allButton;

    @FXML
    private Label allLabel;

    @FXML
    private StackPane bossesButton;

    @FXML
    private Label bossesLabel;

    @FXML
    private Label currentXp;

    @FXML
    private Label levelLabel;

    @FXML
    private Label levelXp;

    @FXML
    private ImageView profilePic;

    @FXML
    private StackPane questsButton;

    @FXML
    private Label questsLabel;

    @FXML
    private Label ritualsLabel;

    @FXML
    private Label username;

    @FXML
    private ProgressBar xpBar;

    @FXML
    private StackPane xpBarContainer;

    @FXML
    private VBox partyCardContainer;

    @FXML
    private FlowPane friendCardContainer;

    @FXML
    private VBox statContainer;

    private Label[] buttons;

    User current = Session.getInstance().getUser();
    private final ProfileViewModel vm = new ProfileViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        buttons = new Label[]{allLabel, ritualsLabel, questsLabel, bossesLabel};

        //set up shiny xp bar
        Rectangle shine = new Rectangle();
        shine.setHeight(10);
        shine.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#D9D9D900")),
                new Stop(0.5, Color.rgb(255, 255, 255, 1.0)),
                new Stop(1, Color.web("#73737300"))
        ));
        shine.widthProperty().bind(xpBar.widthProperty().multiply(xpBar.progressProperty()).subtract(6));
        xpBarContainer.getChildren().add(shine);
        StackPane.setMargin(shine, new Insets(8, 0, 0, 7));

        //FirestoreClient.signUp("TeddySpaghetti", "treatsPlz", "spaghettiCat@meow.com", 5,"wizard_cat.PNG", "MONDAY");
//        FirestoreClient.signUp("Chomper", "treatsPlz", "bigDawg@bark.com", 5,"bard_dog.PNG", "MONDAY");
//        FirestoreClient.signUp("HolyCannoli", "treatsPlz", "holyCannoli@bark.com", 5,"paladin_dog.PNG", "MONDAY");
//        FirestoreClient.signUp("Mr. Bird", "treatsPlz", "mistaBird@tweet.com", 5,"wizard_bird.PNG", "MONDAY");

        username.textProperty().bind(vm.nameProperty());
        profilePic.setImage(vm.getProfilePic());
        levelLabel.textProperty().bind(vm.levelProperty());
        currentXp.textProperty().bind(vm.xpProperty());
        levelXp.textProperty().bind(vm.levelXpProperty());
        xpBar.progressProperty().bind(vm.xpPercentProperty());

        setUpFriendList(vm.getVisibleFriends(), friendCardContainer);
        setUpPartyList(vm.getVisibleParties(), partyCardContainer);

        vm.setUpView();

        statContainer.getChildren().add(new RitualStatCard(vm.getUser()));








    }
    @FXML
    void showAll(MouseEvent event) {
        setActiveButton(allLabel);
    }

    @FXML
    void showBosses(MouseEvent event) {
        setActiveButton(bossesLabel);
    }

    @FXML
    void showQuests(MouseEvent event) {
        setActiveButton(questsLabel);
    }

    @FXML
    void showRituals(MouseEvent event) {
        setActiveButton(ritualsLabel);
    }

    private void setActiveButton(Label button){
        for(Label label:buttons){
            label.getStyleClass().remove("active");
        }

        button.getStyleClass().add("active");
    }

    private void setUpPartyList(ObservableList<Party> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<Party>) change -> {
            container.getChildren().clear();
            for (Party p : list) {
                SmallPartyCard card = new SmallPartyCard(p);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void setUpFriendList(ObservableList<User> list, FlowPane container){
        // reactively render lists:
        list.addListener((ListChangeListener<User>) change -> {
            container.getChildren().clear();
            for (User u : list) {
                SmallFriendCard card = new SmallFriendCard(u);
                container.getChildren().add(card.createView());
            }
        });
    }

}

