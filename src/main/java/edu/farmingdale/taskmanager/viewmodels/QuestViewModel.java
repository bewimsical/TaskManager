package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.MapMark;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseQuestRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import edu.farmingdale.taskmanager.cards.QuestCard;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestViewModel {
    //connect to firebase
    private final FirebaseQuestRepository questRepo = new FirebaseQuestRepository();
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();

    //set up property bindings
    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<Quest> selectedQuest = new SimpleObjectProperty<>();
    private final IntegerProperty nextCompletionIndex = new SimpleIntegerProperty(0);

    private final ObservableList<Quest> visibleQuests = FXCollections.observableArrayList();
    private final ObservableList<Chore> visibleChores = FXCollections.observableArrayList();

    private final Map<String, MapMarkViewModel> choreMarks = new HashMap<>();
    private ChoreCard<Quest> currentQuestCard;

    //set up card images

    //store data from database
    private final Map<String, List<Quest>> quests;
    private final User user;

    public QuestViewModel(){
        Session session = Session.getInstance();
        user = session.getUser();
        quests = session.getQuests();

    }

    public void setUpView(){
        visibleQuests.addAll(quests.get("Active"));
        if (!visibleQuests.isEmpty()) {
            selectedQuest.set(visibleQuests.get(0));
            name.set(getSelectedQuest().getName());
            visibleChores.clear();
            visibleChores.addAll(getSelectedQuest().getChores());
            nextCompletionIndex.set(getSelectedQuest().getNextMarkIndex());
        }

    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<Quest> getVisibleQuests() {
        return visibleQuests;
    }

    public ObservableList<Chore> getVisibleChores() {
        return visibleChores;
    }

    public Map<String, MapMarkViewModel> getChoreMarks() { return choreMarks; }

    public ObjectProperty<Quest> selectedQuestProperty() { return selectedQuest; }
    public Quest getSelectedQuest() { return selectedQuest.get(); }
    public void setSelectedQuest(Quest quest) { selectedQuest.set(quest); }

    public void cardClick(ChoreCard<Quest> card){
        Quest quest = card.getData();
        setSelectedQuest(quest);
        name.set(quest.getName());
        visibleChores.clear();
        visibleChores.addAll(quest.getChores());
        setCurrentQuestCard(card);
        nextCompletionIndex.set(quest.getNextMarkIndex());

        //questRepo.setQuest(quest, user);

        //map image here
    }

    public void switchCategory(String category){
        visibleQuests.clear();
        visibleQuests.setAll(quests.get(category));
        if (!visibleQuests.isEmpty()) {
            selectedQuest.set(visibleQuests.get(0));
            Quest quest = getSelectedQuest();
            name.set(quest.getName());
            visibleChores.clear();
            visibleChores.addAll(quest.getChores());
            nextCompletionIndex.set(quest.getNextMarkIndex());
        }
    }

    public void completeChore(ChoreCard<Chore> card){
        Chore chore = card.getData();
        Quest currentQuest = getSelectedQuest();
        chore.setCompleted(true);
        //set image to completed via chore mark vm

        MapMarkViewModel mvm = getChoreMarks().get(chore.getId());
        mvm.completedProperty().set(true);
        int index = nextCompletionIndex.get();
        mvm.setCompletionIndex(index);
        nextCompletionIndex.set(index + 1);
        currentQuest.setNextMarkIndex(index+1);
        currentQuest.getMapMarks().stream()
                .filter(m -> m.getChoreId().equals(chore.getId()))
                .findFirst()
                .ifPresent(mm -> {
                    mm.setCompletionIndex(index);
                });


        boolean questCompleted = currentQuest.getChores().stream()
                .allMatch(Chore::isCompleted);

        if(questCompleted){
            currentQuest.setCompleted(true);
            quests.get("Complete").add(currentQuest);
            getCurrentQuestCard().redraw();

        }

        card.redraw();

    }


    public ChoreCard<Quest> getCurrentQuestCard() {
        return currentQuestCard;
    }

    public void setCurrentQuestCard(ChoreCard<Quest> currentQuestCard) {
        this.currentQuestCard = currentQuestCard;
    }
}
