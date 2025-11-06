package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public abstract class ChoreCard<T> extends ImageCard<T>{
    protected T data;
    protected String name;
    protected String xp;
    protected Consumer<ChoreCard<T>> onClick;
    protected Image image;
    ImageView imageview;

    public ChoreCard(T data,  Consumer<ChoreCard<T>> onClick) {
        super(data);
        image = new Image(TaskManagerApplication.class.getResource("images/chore card.png").toExternalForm());
        imageview = new ImageView();
        this.onClick = onClick;
        root.setOnMouseClicked(e -> onClick.accept(this));
    }

    @Override
    public Pane createView() {
        return drawCard(60, 422, "chore-card");

    }

    protected Pane drawCard(double height, double width, String cardType){

        imageview.setFitHeight(height);
        imageview.setFitWidth(width);
        imageview.setImage(image);

        Label name  = new Label(this.name);
        name.getStyleClass().add(cardType+"-name");
        name.setPadding(new Insets(-20, 0, -25, 0));
        Label xp = new Label(this.xp + " xp");
        xp.getStyleClass().add(cardType+"-xp");

        VBox container = new VBox(name, xp);
        container.setPadding(new Insets(15, 0, 0, 70));

        root.getChildren().addAll(imageview, container);
        return root;
    }

    @Override
    public void redraw() {
        setCompletedImage();
        imageview.setImage(image);
    }

    protected void setCompletedImage(){
        image =  new Image(TaskManagerApplication.class.getResource("images/chore card - complete.png").toExternalForm());
    }


}
