package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.AttackCard;
import edu.farmingdale.taskmanager.cards.BossCard;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import edu.farmingdale.taskmanager.viewmodels.BossViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class BossController implements Initializable {

    @FXML
    private FlowPane attacksContainer;

    @FXML
    private ImageView bossImageView;

    @FXML
    private Label bossName;

    @FXML
    private VBox bossesContainer;

    @FXML
    private Label bountiesButton;

    @FXML
    private ImageView customBossCard;

    @FXML
    private Label failedButton;

    @FXML
    private ProgressBar healthBar;

    @FXML
    private VBox miniBossesContainer;

    @FXML
    private Label vanquishedButton;

    private Label[] buttons;

    private final BossViewModel vm = new BossViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        buttons = new Label[]{bountiesButton, vanquishedButton, failedButton};

        bossName.textProperty().bind(vm.nameProperty());
        setUpLists(vm.getVisibleBosses(), bossesContainer);
        setUpLists(vm.getVisibleChores(), attacksContainer);
        healthBar.progressProperty().bind(vm.healthPercentProperty());
        bossImageView.imageProperty().bind(vm.bossImageProperty());
        vm.setUpView();
    }

    private void handleBossCardClick(ChoreCard<Boss> clickedBoss){
        vm.cardClick(clickedBoss);
    }

    private void handleAttackCardClick(ChoreCard<Chore> clickedBoss){
        vm.completeChore(clickedBoss);
    }

    @FXML
    void createCustomBoss(MouseEvent event) {

    }

    @FXML
    void toggleActive(MouseEvent e) {
        for(Label label:buttons){
            label.getStyleClass().remove("active");
        }
        Label button = (Label) e.getSource();
        button.getStyleClass().add("active");
        vm.switchCategory(button.getText());
    }

    private void setUpLists(ObservableList<Boss> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<Boss>) change -> {
            container.getChildren().clear();
            //TODO ?????
            vm.setCurrentBossCard(null);
            for (Boss q : list) {
                ChoreCard<Boss> card = new BossCard(q, this::handleBossCardClick);
                if (vm.getCurrentBossCard() == null){
                    vm.setCurrentBossCard(card);
                }
                container.getChildren().add(card.createView());
            }
        });
    }

    private void setUpLists(ObservableList<Chore> list, FlowPane container){
        // reactively render lists:
        list.addListener((ListChangeListener<Chore>) change -> {
            container.getChildren().clear();
            for (Chore q : list) {
                AttackCard card = new AttackCard(q, this::handleAttackCardClick);
                container.getChildren().add(card.createView());
            }
        });
    }

}
