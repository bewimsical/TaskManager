package edu.farmingdale.taskmanager.cards;

import javafx.scene.layout.StackPane;

public abstract class ImageCard extends Card{
    protected StackPane root;

    public ImageCard(Object data) {
        super(data);
        root = new StackPane();
    }

    public abstract void redraw();

}
