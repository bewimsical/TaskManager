package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.BossRecord;
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

        BossRecord bossRecord1 = new BossRecord("Monster!", "300", false);
        BossRecord bossRecord2 = new BossRecord("Monster TWO!", "500", false);
        BossCard card = new BossCard(bossRecord1, this::handleBossCardClick);
        BossCard card2 = new BossCard(bossRecord2,  this::handleBossCardClick);

        bossesContainer.getChildren().addAll(card.createView(), card2.createView());

        BossRecord attack1 = new BossRecord("Attack!", "300", false);
        BossRecord Attack2 = new BossRecord("Attack TWO!", "500", false);
        BossRecord attack3 = new BossRecord("Attack THREE!", "300", false);
        BossRecord Attack4 = new BossRecord("Attack FOUR!", "500", false);
        AttackCard card3 = new AttackCard(attack1, this::handleAttackCardClick);
        AttackCard card4 = new AttackCard(Attack2,  this::handleAttackCardClick);
        AttackCard card5 = new AttackCard(attack1, this::handleAttackCardClick);
        AttackCard card6 = new AttackCard(Attack2,  this::handleAttackCardClick);

        attacksContainer.getChildren().addAll(card3.createView(), card4.createView(), card5.createView(), card6.createView());

        health = 1600;
        healthBar.setProgress(1);
    }

    private void handleBossCardClick(ChoreCard<BossRecord> clickedBoss){
        BossRecord bossRecord = clickedBoss.getData();
        bossName.setText(bossRecord.name());


    }

    private void handleAttackCardClick(ChoreCard<BossRecord> clickedBoss){
        clickedBoss.redraw();
        BossRecord bossRecord = clickedBoss.getData();
        double damage = Double.parseDouble(bossRecord.xp());
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
