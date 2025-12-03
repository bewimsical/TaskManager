package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.*;
import edu.farmingdale.taskmanager.Repositories.FirebaseBossRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseChoreRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BossViewModel {
    //connect to firebase
    private final FirebaseBossRepository bossRepo = new FirebaseBossRepository();
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();
    private final FirebaseChoreRepository choreRepo = new FirebaseChoreRepository();

    //setup property bindings
    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty healthPercent = new SimpleDoubleProperty();
    private final ObjectProperty<Boss> selectedBoss = new SimpleObjectProperty<>();
    private final BooleanProperty vanquished = new SimpleBooleanProperty();
    private final ObservableList<Boss> visibleBosses = FXCollections.observableArrayList();
    private final ObservableList<Chore> visibleChores = FXCollections.observableArrayList();
    private final ObjectProperty<Image> bossImage = new SimpleObjectProperty<>();

    //do I make this  a simple object property
    private ChoreCard<Boss> currentBossCard;
    private double totalHealth;
    private double currentHealth;
    private Image aliveImage;
    private Image deadImage;

    //store data from database
    private final Map<String, List<Boss>> bosses;
    private final User user;

    public BossViewModel(){
        Session session = Session.getInstance();
        user = session.getUser();
        bosses = session.getBosses();

        selectedBoss.addListener((obs, oldBoss, newBoss) -> {
            if (newBoss != null) {
                vanquished.set(newBoss.isVanquished());
                setUpImages(newBoss);
                updateImage();
            }
        });

        // When defeated property changes
        vanquished.addListener((obs, oldVal, newVal) -> {
            Boss b = selectedBoss.get();
            if (b != null) {
                b.setVanquished(newVal);
                setUpImages(b);
            }
            updateImage();
        });
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


    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<Boss> getVisibleBosses() {
        return visibleBosses;
    }

    public ObservableList<Chore> getVisibleChores() {
        return visibleChores;
    }

    public ObjectProperty<Boss> selectedQuestProperty() { return selectedBoss; }

    public Boss getSelectedBoss() { return selectedBoss.get(); }

    public void setSelectedBoss(Boss boss) { selectedBoss.set(boss); }

    public ChoreCard<Boss> getCurrentBossCard() {
        return currentBossCard;
    }

    public void setCurrentBossCard(ChoreCard<Boss> currentQuestCard) {
        this.currentBossCard = currentQuestCard;
    }

    public double getHealthPercent() {
        return healthPercent.get();
    }

    public DoubleProperty healthPercentProperty() {
        return healthPercent;
    }

    public boolean isVanquished() {
        return vanquished.get();
    }

    public BooleanProperty vanquishedProperty() {
        return vanquished;
    }

    public Image getBossImage() {
        return bossImage.get();
    }

    public ObjectProperty<Image> bossImageProperty() {
        return bossImage;
    }

    public void setUpImages(Boss b){
        aliveImage = new Image(TaskManagerApplication.class.getResource("images/monsters/" + b.getDirtyImageUrl()).toExternalForm());
        deadImage = new Image(TaskManagerApplication.class.getResource("images/monsters/" + b.getCleanImageUrl()).toExternalForm());
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

        healthPercent.set(currentHealth / totalHealth);
    }
    private void updateImage() {
        bossImage.set(vanquished.get() ? deadImage : aliveImage);
    }

    public void cardClick(ChoreCard<Boss> card){
        Boss boss = card.getData();
        setSelectedBoss(boss);
        name.set(boss.getName());
        visibleChores.clear();
        visibleChores.addAll(boss.getChores());
        setCurrentBossCard(card);
        setupHealthBar();

        bossRepo.updateBoss(boss, user);

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
        chore.setCompletedTime(LocalDate.now().toString());
        choreRepo.updateChore(chore, user);
        user.addXP(chore.getChoreXP());

        //set health bar things
        currentBoss.setCurrentHealth(currentBoss.getCurrentHealth() - chore.getChoreXP());
        setupHealthBar();

        boolean bossCompleted = currentBoss.getChores().stream()
                .allMatch(Chore::isCompleted);

        if(bossCompleted){
            vanquished.set(true);
            System.out.println("boss defeated??? "+currentBoss.isVanquished());
            bosses.get("Vanquished").add(currentBoss);
            getCurrentBossCard().redraw();
            user.setVanquishedBossCount(user.getVanquishedBossCount()+1);
            user.addXP((int)(currentBoss.getXp()-currentBoss.getTotalHealth()));

        }
        bossRepo.updateBoss(currentBoss, user);
        userRepo.updateUser(user);

        card.redraw();

    }
}
