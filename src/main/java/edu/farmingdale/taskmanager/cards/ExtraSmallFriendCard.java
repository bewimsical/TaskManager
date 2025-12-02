package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ExtraSmallFriendCard extends ImageCard<User> {

    String username;

    public ExtraSmallFriendCard(User data) {
        super(data);
        this.username = data.getUsername();
    }

    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        Image image;
        image = new Image(TaskManagerApplication.class.getResource("images/Friend Card - small.png").toExternalForm());

        ImageView imageview = new ImageView();
        imageview.setFitHeight(165);
        imageview.setFitWidth(165);
        imageview.setImage(image);

        Label name = new Label(this.username);
        name.getStyleClass().add("xs-friend-card-name");
        name.setPadding(new Insets(8, 0, 10, 0));

        Image profilePic;
        profilePic = new Image(TaskManagerApplication.class.getResource("images/"+data.getProfileUrl()).toExternalForm());

        ImageView imageview2 = new ImageView();
        imageview2.setFitHeight(94);
        imageview2.setFitWidth(94);
        imageview2.setImage(profilePic);

        root.setAlignment(Pos.CENTER);
        VBox container = new VBox(name, imageview2);
        container.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(imageview, container);
        return root;
    }
}
