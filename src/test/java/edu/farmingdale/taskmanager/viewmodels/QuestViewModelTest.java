package edu.farmingdale.taskmanager.viewmodels;

import com.google.api.client.util.Maps;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseQuestRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.factories.QuestFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class QuestViewModelTest {

    @Test
    void switchCategory(){
        List<Quest> active = new ArrayList<>();
        List<Quest> completed = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Quest q = new Quest.QuestBuilder()
                    .id("quest"+i)
                    .name("q"+i)
                    .isActive(true)
                    .chores(new ArrayList<>())
                    .build();
            active.add(q);
        }

        for (int i = 2; i < 4; i++) {
            Quest q = new Quest.QuestBuilder()
                    .id("quest"+i)
                    .name("q"+i)
                    .isCompleted(true)
                    .chores(new ArrayList<>())
                    .build();
            completed.add(q);
        }

        Session.getInstance().setQuests(new HashMap<>());
        Map<String, List<Quest>> questsMap = Session.getInstance().getQuests();
        questsMap.put("Active", active);
        questsMap.put("Completed", completed);

        QuestViewModel vm = new QuestViewModel();

        vm.switchCategory("Active");

        ObservableList<Quest> visibleQuests = vm.getVisibleQuests();

        boolean same = visibleQuests.equals(active);

        Assertions.assertTrue(same);

    }

    @Test
    void cardClick(){
        Boss b = new Boss.BossesBuilder()
                .id("boss1")
                .name("Boss")
                .currentHealth(100)
                .totalHealth(100)
                .cleanImageUrl("CleanBogFrog.PNG")
                .dirtyImageUrl("DirtyBogFrog.PNG")
                .chores(new ArrayList<>())
                .build();

        Session.getInstance().setBosses(new HashMap<>());
        Session.getInstance().getBosses().put("Bounties", new ArrayList<>());
        BossViewModel vm = new BossViewModel();

        vm.cardClick(b);

        assertEquals(b.getName(), vm.getName());


    }
}