package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class SearchCard extends Card<User> {

    protected Consumer<SearchCard> onClick;

    public SearchCard(User data, Consumer<SearchCard> onClick) {
        super(data);
        root = new StackPane();

        this.onClick = onClick;
        root.setCursor(Cursor.HAND);
        root.setOnMouseClicked(e -> onClick.accept(this));
    }

    @Override
    public Pane createView() {

        String username = data.getUsername();
        int level = data.getLevel();
        Image image = new Image(TaskManagerApplication.class.getResource("images/search-card.png").toExternalForm());
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(70);
        imageview.setFitWidth(416);

        Label name  = new Label(username);
        name.getStyleClass().add("search-name");
        name.setPadding(new Insets(-20, 0, -25, 0));
        Label levelLabel = new Label("Level: "+level);
        levelLabel.getStyleClass().add("search-level");

        VBox vbox = new VBox(name, levelLabel);
        vbox.setPrefWidth(352);
        vbox.setMinWidth(352);
        vbox.setPadding(new Insets(15,0,0,15));


        root.getChildren().addAll(imageview, vbox);
        return root;
    }


}
