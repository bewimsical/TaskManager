package edu.farmingdale.taskmanager.cards;

import javafx.scene.layout.Pane;

public abstract class Card<T> {
    protected T data;
    protected Pane root;

    public Card(T data){
        this.data = data;
    }

    public abstract Pane createView();

    public T getData() {
        return data;
    }
}
