package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuestStatCard extends StatCard{

    List<Quest> quests;
    public QuestStatCard(User user, List<Quest> quests) {
        super(user);
        this.quests = quests;
        statTitle.setText("Quests");
        statLabel.setText("Completed Quests:");
        stat.setText(String.valueOf(user.getCompletedQuestCount()));

        root.getChildren().addAll(setUpQuests());

    }

    private List<HBox> setUpQuests() {
        List<HBox> questNodes = new ArrayList<>();
        Image doneImage  = new Image(TaskManagerApplication.class.getResource("images/stat-done-quest.png").toExternalForm());
        Image emptyImage = new Image(TaskManagerApplication.class.getResource("images/stat-empty-quest.png").toExternalForm());

        for (int i = 0; i < 3; i++) {
            if (i < quests.size()) {
                Quest q = quests.get(i);

                String title = q.getName();
                Label label = makeSmallLabel(title);

                HBox streakContainer = new HBox();
                streakContainer.setAlignment(Pos.CENTER_LEFT);
                streakContainer.setSpacing(10);
                for (Chore c: q.getChores()){
                    ImageView iv = new ImageView();
                    iv.setImage(c.isCompleted()? doneImage:emptyImage);
                    streakContainer.getChildren().add(iv);
                }

                HBox questNode = new HBox(label, streakContainer);
                questNode.setPadding(new Insets(0,0,0,10));
                questNodes.add(questNode);
            }
        }
        return questNodes;
    }
}
