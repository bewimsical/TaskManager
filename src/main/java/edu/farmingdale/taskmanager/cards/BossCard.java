package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Boss;

import java.util.function.Consumer;

public class BossCard extends ChoreCard<Boss>{

    public BossCard(Boss data, Consumer<ChoreCard<Boss>> onClick) {
        super(data, onClick);
        this.name = data.getName();
        this.xp = data.getXp();
        if(data.isVanquished()){
            setCompletedImage();
        }else{
            setNotCompletedImage();
        }
    }
}
