package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LargeFriendCard extends ImageCard<User>{
    String username;

    public LargeFriendCard(User data) {
        super(data);
        this.username = data.getUsername();
    }


    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        Image image;
        image = new Image(TaskManagerApplication.class.getResource("images/Player Card.png").toExternalForm());

        ImageView imageview = new ImageView();
        imageview.setFitWidth(862);
        imageview.setFitHeight(267);
        imageview.setImage(image);

        Label name = new Label(this.username);
        name.getStyleClass().add("large-friend-card-name");
        name.setPadding(new Insets(0, 0, 10, 0));
        name.setMinWidth(594);
        name.setMaxWidth(594);

        Label levelLabel = new Label("Level: ");
        levelLabel.getStyleClass().add("large-friend-card-name");
        levelLabel.setPadding(new Insets(0, 0, 10, 0));

        Label level = new Label(String.valueOf(data.getLevel()));
        level.getStyleClass().add("large-friend-card-name");
        level.setPadding(new Insets(0, 0, 10, 0));

        HBox header = new HBox(name, levelLabel, level);
        header.setPadding(new Insets(0,0,0,50));

        Image profilePic;
        profilePic = new Image(TaskManagerApplication.class.getResource("images/"+data.getProfileUrl()).toExternalForm());

        ImageView imageview2 = new ImageView();
        imageview2.setFitHeight(154);
        imageview2.setFitWidth(154);
        imageview2.setImage(profilePic);

        Label ritualLabel = new Label("Ritual Streak: ");
        ritualLabel.getStyleClass().add("large-friend-card-info");
        ritualLabel.setPadding(new Insets(-15, 0, 0, 0));

        Label ritualStreak = new Label(String.valueOf(data.getStreak()));
        ritualStreak.getStyleClass().add("large-friend-card-info");
        ritualStreak.setPadding(new Insets(-15, 0, 0, 0));

        HBox ritual = new HBox(ritualLabel, ritualStreak);

        Label questLabel = new Label("Quests Completed: ");
        questLabel.getStyleClass().add("large-friend-card-info");
        questLabel.setPadding(new Insets(-20, 0, 0, 0));

        Label questsCompleted = new Label(String.valueOf(data.getCompletedQuestCount()));
        questsCompleted.getStyleClass().add("large-friend-card-info");
        questsCompleted.setPadding(new Insets(-20, 0, 0, 0));

        HBox quest = new HBox(questLabel, questsCompleted);

        Label bossLabel = new Label("Bosses Slain: ");
        bossLabel.getStyleClass().add("large-friend-card-info");
        bossLabel.setPadding(new Insets(-20, 0, 0, 0));

        Label bossesSlain = new Label(String.valueOf(data.getVanquishedBossCount()));
        bossesSlain.getStyleClass().add("large-friend-card-info");
        bossesSlain.setPadding(new Insets(-20, 0, 0, 0));

        HBox boss = new HBox(bossLabel, bossesSlain);

        VBox dataContainer = new VBox(ritual, quest, boss);

        HBox body = new HBox(imageview2, dataContainer);
        body.setSpacing(15);
        root.setAlignment(Pos.TOP_LEFT);
        VBox container = new VBox(header, body);
        container.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(body, new Insets(-7,0,0,50));
        root.getChildren().addAll(imageview, container);
        return root;
    }
}
