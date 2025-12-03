package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Map;

public class RitualStatCard extends StatCard{
    public RitualStatCard(User user) {
        super(user);

        statTitle.setText("Rituals");
        statLabel.setText("Current Streak:");
        stat.setText(String.valueOf(user.getStreak()));

        weekContainer.getChildren().add(setupStreak());

    }

    private HBox setupStreak(){
        HBox streakContainer = new HBox();
        streakContainer.setSpacing(3);
        Map<String, Boolean> weekStreak = user.getWeekStreak();
        Image doneImage  = new Image(TaskManagerApplication.class.getResource("images/stat-done-streak.png").toExternalForm());
        Image emptyImage = new Image(TaskManagerApplication.class.getResource("images/stat-empty-streak.png").toExternalForm());

        for (boolean val: weekStreak.values()){
            ImageView iv = new ImageView();
            iv.setImage(val? doneImage:emptyImage);
            streakContainer.getChildren().add(iv);
        }


        return streakContainer;
    }
}
