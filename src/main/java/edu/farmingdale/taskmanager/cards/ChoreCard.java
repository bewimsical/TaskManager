package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Boss;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public abstract class ChoreCard<T> extends ImageCard{
    protected T data;
    protected String name;
    protected String xp;
    protected Consumer<T> onClick;

    public ChoreCard(T data,  Consumer<T> onClick) {
        super(data);
        this.onClick = onClick;
        root.setOnMouseClicked(e -> onClick.accept(data));
    }

    @Override
    public Pane createView() {
        Image image = new Image(TaskManagerApplication.class.getResource("images/chore card.png").toExternalForm());
        ImageView imageview = new ImageView();
        imageview.setFitHeight(60);
        imageview.setFitWidth(422);
        imageview.setImage(image);

        Label name  = new Label(this.name);
        name.getStyleClass().add("chore-card-name");
        name.setPadding(new Insets(-20, 0, -25, 0));
        Label xp = new Label(this.xp + " xp");
        xp.getStyleClass().add("chore-card-xp");

        VBox container = new VBox(name, xp);
        container.setPadding(new Insets(15, 0, 0, 70));


        root.getChildren().addAll(imageview, container);
        return root;
    }

    @Override
    public void redraw() {

    }


}
