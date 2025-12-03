package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;

public class QuestStatCard extends StatCard{

    Quest quest;
    public QuestStatCard(User user, Quest quest) {
        super(user);

        statTitle.setText("Quests");
        statLabel.setText("Completed Quests:");
    }
}
