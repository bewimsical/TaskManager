package edu.farmingdale.taskmanager.cards;

import javafx.scene.layout.Pane;

public abstract class Card {
    protected Object data;
    protected Pane root;

    public Card(Object data){
        this.data = data;
    }

    public abstract Pane createView();

}
