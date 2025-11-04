package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Boss;
import edu.farmingdale.taskmanager.cards.BossCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    void createCustomBoss(MouseEvent event) {

    }

    @FXML
    void toggleActive(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Boss boss1 = new Boss("Monster!", "300", false);
        Boss boss2 = new Boss("Monster TWO!", "500", false);
        BossCard card = new BossCard(boss1, this::handleBossCardClick);
        BossCard card2 = new BossCard(boss2,  this::handleBossCardClick);

        Pane cardview = card.createView();

        bossesContainer.getChildren().addAll(cardview, card2.createView());

    }

    private void handleBossCardClick(Boss clickedBoss){
        bossName.setText(clickedBoss.name());
    }

}
