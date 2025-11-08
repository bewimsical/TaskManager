package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Bosses;

import java.util.function.Consumer;

public class AttackCard extends SmallChoreCard<Bosses>{

    public AttackCard(Bosses data, Consumer<ChoreCard<Bosses>> onClick) {
        super(data, onClick);
        this.name = data.getName();
        this.xp = data.getXp();
    }

}
