package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
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

    Map<String, List<Boss>> bosses = Session.getInstance().getBosses();

    Boss currentBoss;

    BossCard currentBossCard;



    @Override
    public void initialize(URL location, ResourceBundle resources){

        buttons = new Label[]{bountiesButton, vanquishedButton, failedButton};


        healthBar.setProgress(1);


        if(bosses != null) {
            for (Boss b:bosses.get("Bounties")){
                BossCard c = new BossCard(b, this::handleBossCardClick);
                bossesContainer.getChildren().add(c.createView());
                if (currentBossCard == null){
                    currentBossCard = c;
                }

            }
            currentBoss = bosses.get("Bounties").get(0);

            bossName.setText(currentBoss.getName());
            Image image;
            if (currentBoss.isVanquished()){
                image = new Image(TaskManagerApplication.class.getResource("images/monsters/" + currentBoss.getCleanImageUrl()).toExternalForm());
            }else {
                image = new Image(TaskManagerApplication.class.getResource("images/monsters/" + currentBoss.getDirtyImageUrl()).toExternalForm());
            }
            bossImageView.setImage(image);
            health = currentBoss.getCurrentHealth();

            for(Chore c: currentBoss.getChores() ){
                AttackCard ac = new AttackCard(c, this::handleAttackCardClick);
                attacksContainer.getChildren().add(ac.createView());
            }
        }



//        Boss b1 = BossFactory.generate("Attack1");
//        Boss b2 =BossFactory.generate("Attack2");
//        Boss b3 =BossFactory.generate("Attack3");
//        Boss b4 =BossFactory.generate("Attack4");
//        AttackCard c1 = new AttackCard(b1, this::handleAttackCardClick);
//        AttackCard c2 = new AttackCard(b2, this::handleAttackCardClick);
//        AttackCard c3 = new AttackCard(b3, this::handleAttackCardClick);
//        AttackCard c4 = new AttackCard(b4, this::handleAttackCardClick);

        //loop through attack list here
//        attacksContainer.getChildren().addAll(c1.createView(),c2.createView(),c3.createView(),c4.createView());

    }

    private void handleBossCardClick(ChoreCard<Boss> clickedBoss){
        currentBossCard = (BossCard) clickedBoss;
        currentBoss = clickedBoss.getData();
        bossName.setText(currentBoss.getName());
        health = currentBoss.getCurrentHealth();
        healthBar.setProgress(currentBoss.getCurrentHealth()/currentBoss.getTotalHealth());
        attacksContainer.getChildren().clear();
        for(Chore c: currentBoss.getChores() ){
            AttackCard ac = new AttackCard(c, this::handleAttackCardClick);
            attacksContainer.getChildren().add(ac.createView());
        }
        Image image;
        if (currentBoss.isVanquished()){
            image = new Image(TaskManagerApplication.class.getResource("images/monsters/" + currentBoss.getCleanImageUrl()).toExternalForm());
        }else {
            image = new Image(TaskManagerApplication.class.getResource("images/monsters/" + currentBoss.getDirtyImageUrl()).toExternalForm());
        }
        bossImageView.setImage(image);





    }

    private void handleAttackCardClick(ChoreCard<Chore> clickedBoss){
        clickedBoss.redraw();
        Chore chore = clickedBoss.getData();
        chore.setCompleted(true);
        double damage = chore.getChoreXP();
        currentBoss.setCurrentHealth(currentBoss.getCurrentHealth()-chore.getChoreXP());
        double next = Math.max(healthBar.getProgress() - (damage/health), 0);
        healthBar.setProgress(next);

        boolean bossDefeated = currentBoss.getChores().stream()
                        .allMatch(Chore::isCompleted);

        if (bossDefeated){
            currentBoss.setVanquished(true);
            bosses.get("Vanquished").add(currentBoss);
            currentBossCard.redraw();
            Image image = new Image(TaskManagerApplication.class.getResource("images/monsters/" + currentBoss.getCleanImageUrl()).toExternalForm());
            bossImageView.setImage(image);
        }


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

        for (Boss b:bosses.get(button.getText())){
            BossCard c = new BossCard(b, this::handleBossCardClick);
            bossesContainer.getChildren().add(c.createView());

        }
    }

}
