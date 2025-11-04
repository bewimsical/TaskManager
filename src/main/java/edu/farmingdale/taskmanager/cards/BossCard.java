package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Boss;

import java.util.function.Consumer;

public class BossCard extends ChoreCard<Boss>{

    public BossCard(Boss data,  Consumer<Boss> onClick) {
        super(data, onClick);
        this.name = data.name();
        this.xp = data.xp();
    }
}
