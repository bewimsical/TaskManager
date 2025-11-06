package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Boss;

import java.util.function.Consumer;

public class AttackCard extends SmallChoreCard<Boss>{

    public AttackCard(Boss data,  Consumer<ChoreCard<Boss>> onClick) {
        super(data, onClick);
        this.name = data.name();
        this.xp = data.xp();
    }

}
