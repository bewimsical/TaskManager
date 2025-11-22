package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class RitualCard extends ChoreCard<Ritual>{

    private Consumer<ChoreCard<Ritual>> onXClick;

    public RitualCard(Ritual data, Consumer<ChoreCard<Ritual>> onClick) {
        super(data, onClick);
        this.name = data.getChore().getName();
        this.xp = data.getXp();
        if(data.isCompleted()){
            setCompletedImage();
        }else{
            setNotCompletedImage();
        }
    }

    public void setOnXClick(Consumer<ChoreCard<Ritual>> onXClick) {
        this.onXClick = onXClick;
    }

    @Override
    protected Pane drawCard(double height, double width, String cardType){

        imageview.setFitHeight(height);
        imageview.setFitWidth(width);
        imageview.setImage(image);

        Label name  = new Label(this.name);
        name.getStyleClass().add(cardType+"-name");
        name.setPadding(new Insets(-20, 0, -25, 0));
        Label xp = new Label(this.xp + " xp");
        xp.getStyleClass().add(cardType+"-xp");

        Image xImage = new Image(TaskManagerApplication.class.getResource("images/X.png").toExternalForm());
        ImageView xView = new ImageView(xImage);
        xView.setFitWidth(20);
        xView.setFitHeight(20);
        //xView.setVisible(false);

        // Create a bigger hitbox
        StackPane hitbox = new StackPane(xView);
        hitbox.setPrefSize(32, 32); // 32×32 clickable area
        hitbox.setMaxSize(32, 32);
        hitbox.setMinSize(32, 32);
        hitbox.setCursor(Cursor.HAND);
        hitbox.setPickOnBounds(true); // allows clicks in transparent area
        hitbox.setVisible(false);

        hitbox.setOnMouseClicked(e -> {
            e.consume(); // prevents the card click from firing
            if (onXClick != null) {
                onXClick.accept(this);
            }
        });

        root.setOnMouseEntered(e -> hitbox.setVisible(true));
        root.setOnMouseExited(e -> hitbox.setVisible(false));


        VBox container = new VBox(name, xp);
        container.setPadding(new Insets(15, 0, 0, 70));
        container.setMinWidth(395);
        container.setMaxWidth(395);

        HBox hBox = new HBox(container, hitbox);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hitbox.setPadding(new Insets(0,10,0,0));

        root.getChildren().addAll(imageview, hBox);
        return root;
    }
}
