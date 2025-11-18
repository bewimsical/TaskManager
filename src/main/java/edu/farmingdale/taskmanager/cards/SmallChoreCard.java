package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public abstract class SmallChoreCard<T> extends ChoreCard<T>{
    public SmallChoreCard(T data, Consumer<ChoreCard<T>> onClick) {
        super(data, onClick);
        image = new Image(TaskManagerApplication.class.getResource("images/chore card - small.png").toExternalForm());
    }

    @Override
    public Pane createView() {
        return drawCard(55, 422, "chore-card");
    }

    @Override
    protected void setCompletedImage(){
        System.out.println("swapping image");
        image =  new Image(TaskManagerApplication.class.getResource("images/chore card - small - completed.png").toExternalForm());
    }

    @Override
    protected void setNotCompletedImage(){
        System.out.println("swapping image");
        image =  new Image(TaskManagerApplication.class.getResource("images/chore card - small.png").toExternalForm());
    }
}
