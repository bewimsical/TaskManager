package edu.farmingdale.taskmanager.cards;

import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;

public abstract class ImageCard<T> extends Card<T>{
    protected StackPane root;

    public ImageCard(T data) {
        super(data);
        root = new StackPane();

    }

    public abstract void redraw();

}
