package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Party;
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

public class LargePartyCard extends ImageCard<Party>{
    String name;
    protected Consumer<LargePartyCard> onClick;

    public LargePartyCard(Party data, Consumer<LargePartyCard> onClick) {
        super(data);
        this.name = data.getName();
        this.onClick = onClick;
        root.setCursor(Cursor.HAND);
        root.setOnMouseClicked(e -> onClick.accept(this));
    }


    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        String partyType = data.getType();
        Image image;
        if (partyType.equals("guild") || partyType.equals("Guild")) {
            image = new Image(TaskManagerApplication.class.getResource("images/large party guild card.png").toExternalForm());
        } else {
            image = new Image(TaskManagerApplication.class.getResource("images/large party alliance card.png").toExternalForm());
        }

        ImageView imageview = new ImageView();
        imageview.setFitWidth(416);
        imageview.setFitHeight(153);
        imageview.setImage(image);

        Label name = new Label(this.name);
        name.getStyleClass().add("large-party-card-name");
        //name.getStyleClass().add("large-party-card-name");
        name.setPadding(new Insets(-5, 0, -8, 15));
        name.setMinWidth(396);
        name.setMaxWidth(396);

        HBox header = new HBox(name);
        //header.setPadding(new Insets(0,0,0,50));


        Label members = new Label("Members: ");
        members.getStyleClass().add("large-friend-card-info");
        members.setPadding(new Insets(-15, 0, -10, 0));

        Label memberCount = new Label(String.valueOf(data.getMembers().size()));
        memberCount.getStyleClass().add("large-friend-card-info");
        memberCount.setPadding(new Insets(-15, 0, -10, 0));

        HBox member = new HBox(members, memberCount);
        VBox.setMargin(member, new Insets(15,0,0,0));
        member.setAlignment(Pos.CENTER);

        Label questLabel = new Label(" Quests");
        questLabel.getStyleClass().add("large-friend-card-info");
        questLabel.setPadding(new Insets(-20, 0, 0, 0));
        //TODO fix this
        Label questsCompleted = new Label(String.valueOf(data.getQuests()));
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
        Label bossesSlain = new Label(String.valueOf(data.getBosses()));
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
        VBox.setMargin(dataContainer, new Insets(0,0,0,0));
        dataContainer.setAlignment(Pos.CENTER);



        root.setAlignment(Pos.TOP_LEFT);
        VBox container = new VBox(header, member, dataContainer);
        container.setAlignment(Pos.TOP_CENTER);
        //VBox.setMargin(body, new Insets(-7,0,0,50));
        root.getChildren().addAll(imageview, container);
        root.setCursor(Cursor.HAND);
        return root;
    }
}
