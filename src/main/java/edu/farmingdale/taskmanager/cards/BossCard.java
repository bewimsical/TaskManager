package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Boss;

import java.util.function.Consumer;

public class BossCard extends ChoreCard<Boss>{

    private Consumer<Boss> onClick;
    public BossCard(Boss data,  Consumer<Boss> onClick) {
        super(data);
        this.name = data.name();
        this.xp = data.xp();
        this.onClick = onClick;

        root.setOnMouseClicked(e -> onClick.accept(data));
    }
}
