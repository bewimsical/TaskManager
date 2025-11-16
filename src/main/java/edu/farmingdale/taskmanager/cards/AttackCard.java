package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Chore;

import java.util.function.Consumer;

public class AttackCard extends SmallChoreCard<Chore>{

    public AttackCard(Chore data, Consumer<ChoreCard<Chore>> onClick) {
        super(data, onClick);
        this.name = data.getName();
        this.xp = data.getChoreXP();
    }

}
