package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

public class RitualStatCard extends StatCard{
    public RitualStatCard(User user) {
        super(user);

        statTitle.setText("Rituals");
        statLabel.setText("Current Streak:");
        stat.setText(String.valueOf(user.getStreak()));
        root.getChildren().add(setupStreak());

    }

    private HBox setupStreak(){
        //TODO: fix this - need to add the this week label and put it all in a hbox wrapper.
        //TODO: set min and max width on label to 340
        Label label = makeLabel("This Week:");
        HBox streakContainer = new HBox();
        streakContainer.setAlignment(Pos.CENTER_LEFT);
        streakContainer.setSpacing(3);
        Map<String, Boolean> weekStreak = user.getWeekStreak();
        Image doneImage  = new Image(TaskManagerApplication.class.getResource("images/stat-done-streak.png").toExternalForm());
        Image emptyImage = new Image(TaskManagerApplication.class.getResource("images/stat-empty-streak.png").toExternalForm());

        for (boolean val: weekStreak.values()){
            ImageView iv = new ImageView();
            iv.setImage(val? doneImage:emptyImage);
            streakContainer.getChildren().add(iv);
        }

        HBox weekContainer = new HBox(label, streakContainer);
        weekContainer.setPadding(new Insets(0,0,0,10));
        weekContainer.setAlignment(Pos.CENTER_LEFT);
        return weekContainer;
    }
}
