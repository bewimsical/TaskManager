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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BossController implements Initializable {

    @FXML
    private StackPane root;

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
    private ProgressBar healthBar;

    @FXML
    private VBox miniBossesContainer;

    @FXML
    private Label vanquishedButton;

    private Label[] buttons;

    private final BossViewModel vm = new BossViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        buttons = new Label[]{bountiesButton, vanquishedButton};

        bossName.textProperty().bind(vm.nameProperty());
        setUpLists(vm.getVisibleBosses(), bossesContainer);
        setUpLists(vm.getVisibleChores(), attacksContainer);
        healthBar.progressProperty().bind(vm.healthPercentProperty());
        bossImageView.imageProperty().bind(vm.bossImageProperty());
        vm.setUpView();
    }

    private void handleBossCardClick(ChoreCard<Boss> clickedBoss){
        Boss boss = clickedBoss.getData();
        vm.cardClick(boss);
        vm.setCurrentBossCard(clickedBoss);
    }

    private void handleAttackCardClick(ChoreCard<Chore> clickedBoss){
        vm.completeChore(clickedBoss);
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
            miniBossesContainer.getChildren().clear();
            //TODO ?????
            vm.setCurrentBossCard(null);
            for (Boss q : list) {
                ChoreCard<Boss> card = new BossCard(q, this::handleBossCardClick);
                if (vm.getCurrentBossCard() == null){
                    vm.setCurrentBossCard(card);
                }
                if(q.getChores().size() < 4){
                    miniBossesContainer.getChildren().add(card.createView());
                }else {
                    container.getChildren().add(card.createView());
                }
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

    @FXML
    void createCustomBoss(MouseEvent event) {
        Rectangle overlay = new Rectangle();
        overlay.setWidth(root.getWidth());
        overlay.setHeight(root.getHeight());
        overlay.setFill(Color.rgb(255, 255, 255, .45));
        overlay.setOnMouseClicked(e -> e.consume()); // block clicks behind

        FXMLLoader loader = new FXMLLoader(TaskManagerApplication.class.getResource("custom-boss-view.fxml"));
        loader.setControllerFactory(param -> new CustomBossController(vm, root));
        Parent popup = null;
        try {
            popup = loader.load();
        } catch (IOException e) {
            System.out.println("Error loading popup");
            System.out.println(e.getMessage());
        }

        // Add overlay + popup to root
        root.getChildren().addAll(overlay, popup);
        StackPane.setAlignment(popup, Pos.CENTER);

//        PartyPopupController controller = loader.getController();
//        controller.setParentContainer(root);
    }



}
