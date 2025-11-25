package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.*;
import edu.farmingdale.taskmanager.Repositories.FirebaseBossRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Map;

public class BossViewModel {
    //connect to firebase
    private final FirebaseBossRepository bossRepo = new FirebaseBossRepository();
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();

    //setup property bindings
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty xpPercent = new SimpleDoubleProperty();
    private final ObjectProperty<Boss> selectedBoss = new SimpleObjectProperty<>();
    private final ObservableList<Boss> visibleBosses = FXCollections.observableArrayList();
    private final ObservableList<Chore> visibleChores = FXCollections.observableArrayList();

    //do i make this  a simple object property
    private ChoreCard<Boss> currentBossCard;
    private double totalHealth;
    private double currentHealth;

    //store data from database
    private final Map<String, List<Boss>> bosses;
    private final User user;

    public BossViewModel(){
        Session session = Session.getInstance();
        user = session.getUser();
        bosses = session.getBosses();
    }

    public void setUpView(){
        visibleBosses.addAll(bosses.get("Bounties"));
        if (!visibleBosses.isEmpty()) {
            selectedBoss.set(visibleBosses.get(0));
            name.set(getSelectedBoss().getName());
            visibleChores.clear();
            visibleChores.addAll(getSelectedBoss().getChores());
            setupHealthBar();

        }
    }
    private void setupHealthBar(){
//        totalHealth = visibleChores.stream()
//                .mapToInt(Chore::getChoreXP)
//                .sum();
        totalHealth = getSelectedBoss().getTotalHealth();

//        currentHealth = visibleChores.stream()
//                .filter(c -> !c.isCompleted())
//                .mapToInt(Chore::getChoreXP)
//                .sum();
        currentHealth = getSelectedBoss().getCurrentHealth();

        xpPercent.set(currentHealth / totalHealth);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<Boss> getVisibleQuests() {
        return visibleBosses;
    }

    public ObservableList<Chore> getVisibleChores() {
        return visibleChores;
    }

    public ObjectProperty<Boss> selectedQuestProperty() { return selectedBoss; }
    public Boss getSelectedBoss() { return selectedBoss.get(); }
    public void setSelectedQuest(Boss boss) { selectedBoss.set(boss); }

    public ChoreCard<Boss> getCurrentQuestCard() {
        return currentBossCard;
    }

    public void setCurrentQuestCard(ChoreCard<Boss> currentQuestCard) {
        this.currentBossCard = currentQuestCard;
    }

    public void cardClick(ChoreCard<Boss> card){
        Boss boss = card.getData();
        setSelectedQuest(boss);
        name.set(boss.getName());
        visibleChores.clear();
        visibleChores.addAll(boss.getChores());
        setCurrentQuestCard(card);
        setupHealthBar();


        //questRepo.setQuest(quest, user);

        //map image here
    }

    public void switchCategory(String category){
        visibleBosses.clear();
        visibleBosses.setAll(bosses.get(category));
        if (!visibleBosses.isEmpty()) {
            selectedBoss.set(visibleBosses.get(0));
            Boss boss = getSelectedBoss();
            name.set(boss.getName());
            visibleChores.clear();
            visibleChores.addAll(boss.getChores());
            //TODO hmm gow do i get current card??
            setupHealthBar();
        }
    }

    public void completeChore(ChoreCard<Chore> card){
        Chore chore = card.getData();
        Boss currentBoss = getSelectedBoss();
        chore.setCompleted(true);

        //set health bar things
        currentBoss.setCurrentHealth(currentBoss.getCurrentHealth() - chore.getChoreXP());
        setupHealthBar();

        boolean bossCompleted = currentBoss.getChores().stream()
                .allMatch(Chore::isCompleted);

        if(bossCompleted){
            currentBoss.setVanquished(true);
            bosses.get("Vanquished").add(currentBoss);
            getCurrentQuestCard().redraw();

        }

        card.redraw();

    }


}
