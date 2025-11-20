package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Ritual;

import java.util.function.Consumer;

public class RitualCard extends ChoreCard<Ritual>{
    public RitualCard(Ritual data, Consumer<ChoreCard<Ritual>> onClick) {
        super(data, onClick);
        this.name = data.getChore().getName();
        this.xp = data.getChore().getChoreXP();
        if(data.isCompleted()){
            setCompletedImage();
        }else{
            setNotCompletedImage();
        }
    }
}
