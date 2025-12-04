package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseQuestRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
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
    Session session = Session.getInstance();
    User user;
    Map<String, List<Quest>> quests;


    @BeforeEach
    void newSetupTest() {
        user = session.getUser();
        quests = session.getQuests();
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

    @Test
    void nameLoadsOnStartup() {
        var quest = new QuestViewModel();
        assertNotNull(quest.nameProperty().getName());
    }
}