package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Boss;

import java.util.function.Consumer;

public class AttackCard extends SmallChoreCard<Boss>{

    public AttackCard(Boss data, Consumer<ChoreCard<Boss>> onClick) {
        super(data, onClick);
        this.name = data.getName();
        this.xp = data.getXp();
    }

}
