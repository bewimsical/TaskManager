package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Party;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SmallPartyCard extends ImageCard{

    String name;
    String type;
    public SmallPartyCard(Party data) {
        super(data);
        this.name = data.getName();
        this.type = data.getType();
    }

    @Override
    public void redraw() {

    }

    @Override
    public Pane createView() {
        Image image;
        if (type.equals("guild")) {
            image = new Image(TaskManagerApplication.class.getResource("images/party card - guild.png").toExternalForm());
        } else {
            image = new Image(TaskManagerApplication.class.getResource("images/party card - alliance.png").toExternalForm());
        }
        ImageView imageview = new ImageView();
        imageview.setFitHeight(58);
        imageview.setFitWidth(569);
        imageview.setImage(image);

        Label name  = new Label(this.name);
        name.getStyleClass().add("small-party-card-name");
        name.setPadding(new Insets(-12, 0, -10, 15));

        root.setAlignment(Pos.CENTER_LEFT);
        root.setPadding(new Insets(0,0,0,8));




        root.getChildren().addAll(imageview, name);
        return root;
    }
}
