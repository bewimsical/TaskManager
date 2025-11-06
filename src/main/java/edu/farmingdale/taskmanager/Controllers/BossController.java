package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Boss;
import edu.farmingdale.taskmanager.cards.AttackCard;
import edu.farmingdale.taskmanager.cards.BossCard;
import edu.farmingdale.taskmanager.cards.ChoreCard;
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

    private Label[] buttons;

    private double health;



    @Override
    public void initialize(URL location, ResourceBundle resources){

        buttons = new Label[]{bountiesButton, vanquishedButton, failedButton};

        Boss boss1 = new Boss("Monster!", "300", false);
        Boss boss2 = new Boss("Monster TWO!", "500", false);
        BossCard card = new BossCard(boss1, this::handleBossCardClick);
        BossCard card2 = new BossCard(boss2,  this::handleBossCardClick);

        bossesContainer.getChildren().addAll(card.createView(), card2.createView());

        Boss attack1 = new Boss("Attack!", "300", false);
        Boss Attack2 = new Boss("Attack TWO!", "500", false);
        Boss attack3 = new Boss("Attack THREE!", "300", false);
        Boss Attack4 = new Boss("Attack FOUR!", "500", false);
        AttackCard card3 = new AttackCard(attack1, this::handleAttackCardClick);
        AttackCard card4 = new AttackCard(Attack2,  this::handleAttackCardClick);
        AttackCard card5 = new AttackCard(attack1, this::handleAttackCardClick);
        AttackCard card6 = new AttackCard(Attack2,  this::handleAttackCardClick);

        attacksContainer.getChildren().addAll(card3.createView(), card4.createView(), card5.createView(), card6.createView());

        health = 1600;
        healthBar.setProgress(1);
    }

    private void handleBossCardClick(ChoreCard<Boss> clickedBoss){
        Boss boss = clickedBoss.getData();
        bossName.setText(boss.name());


    }

    private void handleAttackCardClick(ChoreCard<Boss> clickedBoss){
        clickedBoss.redraw();
        Boss boss = clickedBoss.getData();
        double damage = Double.parseDouble(boss.xp());
        double next = Math.max(healthBar.getProgress() - (damage/health), 0);
        healthBar.setProgress(next);

        System.out.printf("""
                Health: %f
                Damage: %f
                Next: %f
                Progress Bar: %f
                """, health, damage, next, healthBar.getProgress());

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
    }

}
