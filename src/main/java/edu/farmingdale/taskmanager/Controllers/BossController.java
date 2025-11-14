package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.BossFactory;
import edu.farmingdale.taskmanager.FirestoreClient;
import edu.farmingdale.taskmanager.Models.Bosses;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.AttackCard;
import edu.farmingdale.taskmanager.cards.BossCard;
import edu.farmingdale.taskmanager.cards.ChoreCard;
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

    private double health;

    User current = Session.getInstance().getUser();

    Map<String, List<Bosses>> bosses = Session.getInstance().getBosses();



    @Override
    public void initialize(URL location, ResourceBundle resources){

        buttons = new Label[]{bountiesButton, vanquishedButton, failedButton};

        health = 1200;
        healthBar.setProgress(1);



        for (Bosses b:bosses.get("Bounties")){
            BossCard c = new BossCard(b, this::handleBossCardClick);
            bossesContainer.getChildren().add(c.createView());

        }
        Bosses currentBoss = bosses.get("Bounties").get(0);

        bossName.setText(currentBoss.getName());
        Image image = new Image(TaskManagerApplication.class.getResource("images/"+currentBoss.getDirtyImageUrl()).toExternalForm());
        bossImageView.setImage(image);

        Bosses b1 = BossFactory.generate("Attack1");
        Bosses b2 =BossFactory.generate("Attack2");
        Bosses b3 =BossFactory.generate("Attack3");
        Bosses b4 =BossFactory.generate("Attack4");
        AttackCard c1 = new AttackCard(b1, this::handleAttackCardClick);
        AttackCard c2 = new AttackCard(b2, this::handleAttackCardClick);
        AttackCard c3 = new AttackCard(b3, this::handleAttackCardClick);
        AttackCard c4 = new AttackCard(b4, this::handleAttackCardClick);

        //loop through attack list here
        attacksContainer.getChildren().addAll(c1.createView(),c2.createView(),c3.createView(),c4.createView());

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

        bossesContainer.getChildren().clear();

        for (Bosses b:bosses.get(button.getText())){
            BossCard c = new BossCard(b, this::handleBossCardClick);
            bossesContainer.getChildren().add(c.createView());

        }
    }

}
