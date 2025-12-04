package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BossStatCard extends StatCard{

    List<Boss> bosses;
    public BossStatCard(User user,List<Boss> bosses) {
        super(user);
        this.bosses = bosses;
        statTitle.setText("Bosses");
        statLabel.setText("Vanquished Bosses:");
        stat.setText(String.valueOf(user.getVanquishedBossCount()));

        root.getChildren().addAll(setUpBosses());



    }

    private List<HBox> setUpBosses() {
        List<HBox> bossNodes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (i < bosses.size()) {
                Boss b = bosses.get(i);

                String title = b.getName();
                Label label = makeSmallLabel(title);

                Rectangle border = new Rectangle(208, 30);
                border.setFill(Color.web("#8f5902"));
                border.setStroke(Color.web("#472c01"));
                ProgressBar healthBar = new ProgressBar();
                healthBar.setPrefHeight(20);
                healthBar.setPrefWidth(198);
                healthBar.getStyleClass().add("health-bar");
                healthBar.setProgress((double) (b.getCurrentHealth()/b.getTotalHealth()));

                StackPane healthBarContainer = new StackPane(border, healthBar);


                HBox bossNode = new HBox(label, healthBarContainer);
                bossNode.setPadding(new Insets(0,0,0,10));
                bossNodes.add(bossNode);
            }
        }
        return bossNodes;
    }
}
