package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.BossRecord;

import java.util.function.Consumer;

public class BossCard extends ChoreCard<BossRecord>{

    public BossCard(BossRecord data, Consumer<ChoreCard<BossRecord>> onClick) {
        super(data, onClick);
        this.name = data.name();
        this.xp = data.xp();
    }
}
