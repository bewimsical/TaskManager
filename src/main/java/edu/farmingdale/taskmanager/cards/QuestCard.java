package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Quest;

import java.util.function.Consumer;

public class QuestCard extends ChoreCard<Quest> {

    public QuestCard(Quest data, Consumer<ChoreCard<Quest>> onClick) {
        super(data, onClick);
        this.name = data.getName();
        this.xp = data.getXp();
        if(data.isCompleted()){
            setCompletedImage();
        }else{
            setNotCompletedImage();
        }
    }
}
