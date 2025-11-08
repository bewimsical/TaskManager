package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Party;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LargePartyCard extends ImageCard<Party>{
    String name;

    public LargePartyCard(Party data) {
        super(data);
        this.name = data.getName();
    }


    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        Image image;
        image = new Image(TaskManagerApplication.class.getResource("images/Party Card.png").toExternalForm());

        ImageView imageview = new ImageView();
        imageview.setFitWidth(416);
        imageview.setFitHeight(166);
        imageview.setImage(image);

        Label name = new Label(this.name);
        name.getStyleClass().add("large-friend-card-name");
        name.getStyleClass().add("large-party-card-name");
        name.setPadding(new Insets(0, 0, 10, 0));
        name.setMinWidth(396);
        name.setMaxWidth(396);
        name.setAlignment(Pos.CENTER);

        HBox header = new HBox(name);
        //header.setPadding(new Insets(0,0,0,50));

        Label type = new Label(data.getType());
        type.getStyleClass().add("large-friend-card-info");
        type.setPadding(new Insets(-15, 0, 0, 0));
        type.setAlignment(Pos.CENTER_LEFT);

        Label members = new Label("Members: ");
        members.getStyleClass().add("large-friend-card-info");
        members.setPadding(new Insets(-15, 0, 0, 0));

        Label memberCount = new Label(String.valueOf(data.members()));
        memberCount.getStyleClass().add("large-friend-card-info");
        memberCount.setPadding(new Insets(-15, 0, 0, 0));

        HBox member = new HBox(members, memberCount);

        Label questLabel = new Label(" Quests");
        questLabel.getStyleClass().add("large-friend-card-info");
        questLabel.setPadding(new Insets(-20, 0, 0, 0));
        //TODO fix this
        Label questsCompleted = new Label(String.valueOf(data.quests()));
        questsCompleted.getStyleClass().add("large-friend-card-info");
        questsCompleted.setPadding(new Insets(-20, 0, 0, 5));

        Image questGemImage;
        questGemImage = new Image(TaskManagerApplication.class.getResource("images/quest-gem.png").toExternalForm());

        ImageView questGem = new ImageView();
        questGem.setFitWidth(36);
        questGem.setFitHeight(36);
        questGem.setImage(questGemImage);

        HBox quest = new HBox(questGem,questsCompleted,questLabel);

        Label bossLabel = new Label(" Bosses");
        bossLabel.getStyleClass().add("large-friend-card-info");
        bossLabel.setPadding(new Insets(-20, 0, 0, 0));
        //todo fix this
        Label bossesSlain = new Label(String.valueOf(data.bosses()));
        bossesSlain.getStyleClass().add("large-friend-card-info");
        bossesSlain.setPadding(new Insets(-20, 0, 0, 5));

        Image bossGemImage;
        bossGemImage = new Image(TaskManagerApplication.class.getResource("images/quest-gem.png").toExternalForm());

        ImageView bossGem = new ImageView();
        bossGem.setFitWidth(36);
        bossGem.setFitHeight(36);
        bossGem.setImage(bossGemImage);

        HBox boss = new HBox(bossGem,bossesSlain,bossLabel);

        HBox dataContainer = new HBox(quest, boss);
        dataContainer.setSpacing(20);



        root.setAlignment(Pos.TOP_LEFT);
        VBox container = new VBox(header, type, member, dataContainer);
        container.setAlignment(Pos.TOP_CENTER);
        //VBox.setMargin(body, new Insets(-7,0,0,50));
        root.getChildren().addAll(imageview, container);
        return root;
    }
}
