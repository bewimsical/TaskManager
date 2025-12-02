package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class MediumFriendCard extends ImageCard<User>{
    String username;
    protected Consumer<MediumFriendCard> onClick;

    public MediumFriendCard(User data, Consumer<MediumFriendCard> onClick ) {
        super(data);
        this.username = data.getUsername();
        this.onClick = onClick;
        root.setCursor(Cursor.HAND);
        root.setOnMouseClicked(e -> onClick.accept(this));
    }


    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        Image image;
        image = new Image(TaskManagerApplication.class.getResource("images/Player Card.png").toExternalForm());

        ImageView imageview = new ImageView();
        imageview.setFitWidth(500);
        imageview.setPreserveRatio(true);
        //imageview.setFitHeight(267);
        imageview.setImage(image);

        Label name = new Label(this.username);
        //change style class
        name.getStyleClass().add("med-friend-card-name");
        name.setPadding(new Insets(-3, 0, 0, 0));
        name.setMinWidth(345);
        name.setMaxWidth(345);

        //change style class
        Label levelLabel = new Label("Level: ");
        levelLabel.getStyleClass().add("med-friend-card-name");
        levelLabel.setPadding(new Insets(-3, 0, 0, 0));

        //change style class
        Label level = new Label(String.valueOf(data.getLevel()));
        level.getStyleClass().add("med-friend-card-name");
        level.setPadding(new Insets(-3, 0, 0, 0));

        HBox header = new HBox(name, levelLabel, level);
        header.setPadding(new Insets(0,0,0,30));

        Image profilePic;
        profilePic = new Image(TaskManagerApplication.class.getResource("images/"+data.getProfileUrl()).toExternalForm());

        ImageView imageview2 = new ImageView();
        imageview2.setFitHeight(90);
        imageview2.setFitWidth(90);
        imageview2.setImage(profilePic);

        //change style class
        Label ritualLabel = new Label("Ritual Streak: ");
        ritualLabel.getStyleClass().add("med-friend-card-info");
        ritualLabel.setPadding(new Insets(-10, 0, 0, 0));

        Label ritualStreak = new Label(String.valueOf(data.getStreak()));
        ritualStreak.getStyleClass().add("med-friend-card-info");
        ritualStreak.setPadding(new Insets(-10, 0, 0, 0));

        HBox ritual = new HBox(ritualLabel, ritualStreak);

        Label questLabel = new Label("Quests Completed: ");
        questLabel.getStyleClass().add("med-friend-card-info");
        questLabel.setPadding(new Insets(-15, 0, 0, 0));

        Label questsCompleted = new Label(String.valueOf(data.getCompletedQuestCount()));
        questsCompleted.getStyleClass().add("med-friend-card-info");
        questsCompleted.setPadding(new Insets(-15, 0, 0, 0));

        HBox quest = new HBox(questLabel, questsCompleted);

        Label bossLabel = new Label("Bosses Slain: ");
        bossLabel.getStyleClass().add("med-friend-card-info");
        bossLabel.setPadding(new Insets(-15, 0, 0, 0));

        Label bossesSlain = new Label(String.valueOf(data.getVanquishedBossCount()));
        bossesSlain.getStyleClass().add("med-friend-card-info");
        bossesSlain.setPadding(new Insets(-15, 0, 0, 0));

        HBox boss = new HBox(bossLabel, bossesSlain);

        VBox dataContainer = new VBox(ritual, quest, boss);

        HBox body = new HBox(imageview2, dataContainer);
        body.setSpacing(10);
        root.setAlignment(Pos.TOP_LEFT);
        VBox container = new VBox(header, body);
        container.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(body, new Insets(2,0,0,35));
        root.getChildren().addAll(imageview, container);
        return root;
    }
}
