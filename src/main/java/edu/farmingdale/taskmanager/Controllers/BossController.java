package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.BossRecord;
import edu.farmingdale.taskmanager.Models.Bosses;
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


        //loop through boss list here
        bossesContainer.getChildren().addAll();


        //loop through attack list here
        attacksContainer.getChildren().addAll();

        health = 1600;
        healthBar.setProgress(1);
    }

    private void handleBossCardClick(ChoreCard<Bosses> clickedBoss){
        Bosses boss = clickedBoss.getData();
        bossName.setText(boss.getName());


    }

    private void handleAttackCardClick(ChoreCard<Bosses> clickedBoss){
        clickedBoss.redraw();
        Bosses boss = clickedBoss.getData();
        double damage = boss.getXp();
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
