package edu.farmingdale.taskmanager.viewmodels;

import com.google.api.client.util.Maps;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseQuestRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.factories.QuestFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class QuestViewModelTest {

    final FirebaseQuestRepository questRepo = new FirebaseQuestRepository();
    final FirebaseUserRepository userRepo = new FirebaseUserRepository();
    private final ObservableList<Quest> visibleQuests = FXCollections.observableArrayList();
    private final ObservableList<Chore> visibleChores = FXCollections.observableArrayList();
    QuestViewModel vm = new QuestViewModel();
    Session session = Session.getInstance();
    User user;
    User user1;
    Map<String, List<Quest>> quests;
    //Quest quests1;
    //quests = session.getQuests();


    //QuestViewModel vm = new QuestViewModel();
    //Quest q1 = QuestFactory.generate();
    //Quest q2 = QuestFactory.generate();
    //Quest q3 = QuestFactory.generate();
    //Quest q4 = QuestFactory.generate();
    //List<Quest> activeQuests = List.of(q1, q2);
    //List<Quest> inactiveQuests = List.of(q3, q4);

    /*
    Map<String, List<Quest>> questMap = Maps.newHashMap();
    questMap.put("Active", activeQuests);
    questMap.put("Completed", inactiveQuests);

    questMap =  vm.getQuests();

     */



    @BeforeEach
    void newSetupTest() {

        user = session.getUser();
        //quests1.setName("new quest");
        //quests1.setActive(true);
        //quests.
        //quests.get("Complete").add(currentQuest);
        visibleQuests.addAll();
        visibleChores.addAll();

        /*
        QuestViewModel vm = new QuestViewModel();
        Quest q1 = QuestFactory.generate();
        Quest q2 = QuestFactory.generate();
        Quest q3 = QuestFactory.generate();
        Quest q4 = QuestFactory.generate();
        List<Quest> activeQuests = List.of(q1, q2);
        List<Quest> inactiveQuests = List.of(q3, q4);

        Map<String, List<Quest>> quests = Maps.newHashMap();
        quests.put("Active", activeQuests);
        quests.put("Completed", inactiveQuests);

        quests =  vm.getQuests();
        */
    }


    @Test
    void choresNotEmptyTest() {
        var quest = new QuestViewModel();
        assertEquals(!quest.getVisibleChores().isEmpty(), !quest.getVisibleQuests().isEmpty());
    }

    @Test
    void namesNotEmptyTest() {
        var quest = new QuestViewModel();
        assertNotNull(quest.nameProperty().isEmpty());
    }

    /*
    @Test
    void switchCategory() {
        var quest = new QuestViewModel();
        //Map<String, List<Quest>> quests = Maps.newHashMap();
        //quests.put("Active", activeQuests);
        //quests.put("Completed", inactiveQuests);
        //visibleQuests.

        //quests =  vm.getQuests();
        //quest.setUpView();

        assertEquals(quests.get("Active"), visibleQuests);


    }

     */
}