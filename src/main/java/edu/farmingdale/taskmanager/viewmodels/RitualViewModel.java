package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseRitualRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

public class RitualViewModel {
    //TODO - add functionality to remove rituals.

    //connect to firebase
    private final FirebaseRitualRepository ritualRepo = new FirebaseRitualRepository();
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();

    //set up property bindings
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty streak = new SimpleStringProperty();
    private final StringProperty xpBonus = new SimpleStringProperty();
    private final DoubleProperty xpPercent = new SimpleDoubleProperty();
    private final ObservableList<Ritual> morningRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> middayRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> eveningRituals = FXCollections.observableArrayList();
    private final Map<String, ObjectProperty<Image>> dayImages = new HashMap<>();

    //set up card images
    private final Image doneImage  = new Image(TaskManagerApplication.class.getResource("images/ritualgem.png").toExternalForm());
    private final Image emptyImage = new Image(TaskManagerApplication.class.getResource("images/retualgem-empty2.png").toExternalForm());

    //store data from database
    private final Map<String, List<Ritual>> rituals;
    private final Map<String, Boolean> weekStreak;
    private final User user;

    private int totalXP;
    private int completedXP;

    public RitualViewModel() {
        //get data from session
        Session session = Session.getInstance();
        user = session.getUser();
        rituals = session.getRituals();
        weekStreak = user.getWeekStreak();

        //add keys to day images map
        for (int i = 1; i <= 7; i++) {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();
            dayImages.put(String.valueOf(i), property);
        }
        //set values for properties
        date.set(formatDate(LocalDate.now()));
        streak.set(String.valueOf(user.getStreak()));
        xpBonus.set(String.valueOf(user.getXpBonus()));

        totalXP = rituals.values().stream()
                .flatMap(List::stream)
                .mapToInt(Ritual::getXp)
                .sum();

        completedXP = rituals.values().stream()
                .flatMap(List::stream)
                .filter(Ritual::isCompleted)
                .mapToInt(Ritual::getXp)
                .sum();

        xpPercent.set((double)completedXP/totalXP);

    }

    //Make bindings work on start - need to make change to see change?
    public void setUpViews(){
        morningRituals.addAll(rituals.get("Morning"));
        middayRituals.addAll(rituals.get("Midday"));
        eveningRituals.addAll(rituals.get("Evening"));

        for (int i = 1; i <= 7; i++) {
            updateDay(String.valueOf(i));
        }
    }

    //getters for properties
    public ObjectProperty<Image> imageProperty(String day) {
        return dayImages.get(day);
    }

    public StringProperty dateProperty(){
        return date;
    }

    public StringProperty streakProperty(){
        return streak;
    }

    public StringProperty xpBonusProperty(){
        return xpBonus;
    }

    public DoubleProperty getXpPercentProperty(){
        return xpPercent;
    }

    public ObservableList<Ritual> getMorningRituals() {
        return morningRituals;
    }

    public ObservableList<Ritual> getMiddayRituals() {
        return middayRituals;
    }

    public ObservableList<Ritual> getEveningRituals() {
        return eveningRituals;
    }

    public Map<String, List<Ritual>> getRituals() {
        return rituals;
    }

    public Map<String, Boolean> getWeekStreak() {
        return weekStreak;
    }


    //date helper methods
    public void updateDay(String day) {
        boolean value = weekStreak.getOrDefault(day, false);
        dayImages.get(day).set(value ? doneImage : emptyImage);
    }

    private String formatDate(LocalDate date){
        String weekday = date.getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String month = date.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String ordinal = addOrdinal(date.getDayOfMonth());

        return weekday + ", " + month + " " + ordinal;
    }

    private String addOrdinal(int day) {
        if (day >= 11 && day <= 13) return day + "th";
        switch (day % 10) {
            case 1: return day + "st";
            case 2: return day + "nd";
            case 3: return day + "rd";
            default: return day + "th";
        }
    }

    //logic for creating a ritual
    public void createRitual(Chore chore, String time){
        //check if chores are already completed today. if so undo c
        if (allChoresComplete()){
            String day = String.valueOf(LocalDate.now().getDayOfWeek().getValue());
            weekStreak.put(day, false);
            updateDay(day);
            user.setStreak(user.getStreak()-1);
            //TODO how much xp bonus do we earn???
            user.setXpBonus(user.getXpBonus()-0.25);
            int xpGained = (int)((totalXP*user.getXpBonus()) - totalXP);
            user.setXp(user.getXp()-xpGained);
            //update view
            streak.set(String.valueOf(user.getStreak()));
            xpBonus.set(String.valueOf(user.getXpBonus()));
            //update database
            userRepo.updateUser(user);
        }

        String ritualId = UUID.randomUUID().toString();
        Ritual ritual = new Ritual.RitualBuilder()
                .id(ritualId)
                .chores(chore)
                .timeOfDay(time)
                .xp(100)
                .build();
        ritualRepo.setRitual(ritual, user);
        //add ritual to list
        switch (ritual.getTimeOfDay().toString()){
            case "Morning" -> {
                morningRituals.add(ritual);
                rituals.get("Morning").add(ritual);
            }
            case "Midday" -> {
                middayRituals.add((ritual));
                rituals.get("Midday").add(ritual);
            }
            case "Evening" -> {
                eveningRituals.add((ritual));
                rituals.get("Evening").add(ritual);
            }
        }
        totalXP += ritual.getXp();
        xpPercent.set((double)completedXP/totalXP);
        //add ritual to map
    }

    //logic for completing a ritual
    public void completeChore(ChoreCard choreCard){
        Ritual ritual = (Ritual) choreCard.getData();
        if (!ritual.isCompleted()) {
            ritual.setCompleted(true);
            ritual.setDateRecorded(LocalDate.now().toString());
            ritual.getChore().setCompletedTime(LocalDate.now().toString());
            ritualRepo.updateRitual(ritual, user);

            //move xp bar
            completedXP += ritual.getXp();
            user.setXp(user.getXp() + ritual.getXp());
            xpPercent.set((double) completedXP / totalXP);
            //check all chores
            boolean dayComplete = rituals.values().stream()
                    .flatMap(List::stream)
                    .allMatch(Ritual::isCompleted);

            //TODO consider extracting this to a method
            if (dayComplete) {
                //update database
                String day = String.valueOf(LocalDate.now().getDayOfWeek().getValue());
                weekStreak.put(day, true);
                updateDay(day);
                int xpGained = (int) (totalXP * user.getXpBonus());
                user.setXp(user.getXp() + xpGained);
                user.setStreak(user.getStreak() + 1);
                //TODO how much xp bonus do we earn???
                user.setXpBonus(getStreakMultiplier(user.getStreak()));

                //update view
                streak.set(String.valueOf(user.getStreak()));
                xpBonus.set(String.valueOf(user.getXpBonus()));
                //update database
            }
            userRepo.updateUser(user);

            choreCard.redraw();
        }
    }

    private boolean allChoresComplete(){
        return rituals.values().stream()
                .flatMap(List::stream)
                .allMatch(Ritual::isCompleted);
    }

    private double getStreakMultiplier(int streak) {
        double growth = 1 + (0.08 * streak);        // strong linear growth
        double curve = 1 + Math.log1p(streak) * 0.15; // gentle scaling bump
        double mult = (growth + curve) / 2;         // blend them for smoothness
        mult = Math.round(mult * 100.0) / 100.0;
        return Math.min(mult, 2.5);                 // hard cap
    }

    public void deleteRitual(Ritual ritual){
        System.out.println("Deleting ritual");
        ritualRepo.deleteRitual(ritual, user, ()-> onRitualDeleted(ritual));

    }

    private void onRitualDeleted(Ritual ritual){
        //remove the card
        boolean dayCompleBefore = allChoresComplete();
        switch (ritual.getTimeOfDay().toString()){
            case "Morning" -> {
                morningRituals.remove(ritual);
                rituals.get("Morning").remove(ritual);
            }
            case "Midday" -> {
                middayRituals.remove((ritual));
                rituals.get("Midday").remove(ritual);
            }
            case "Evening" -> {
                eveningRituals.remove((ritual));
                rituals.get("Evening").remove(ritual);
            }
        }
        //update the total xp
        totalXP -= ritual.getXp();
        if (ritual.isCompleted()){
            completedXP -= ritual.getXp();
        }
        xpPercent.set((double) completedXP / totalXP);
        //check for completed and then possibly do the completed things
        boolean dayComplete = allChoresComplete();
        if (dayComplete && !dayCompleBefore) {
            //update database
            String day = String.valueOf(LocalDate.now().getDayOfWeek().getValue());
            weekStreak.put(day, true);
            updateDay(day);
            int xpGained = (int) (totalXP * user.getXpBonus());
            user.setXp(user.getXp() + xpGained);
            user.setStreak(user.getStreak() + 1);
            //TODO how much xp bonus do we earn???
            user.setXpBonus(getStreakMultiplier(user.getStreak()));

            //update view
            streak.set(String.valueOf(user.getStreak()));
            xpBonus.set(String.valueOf(user.getXpBonus()));
            //update database
        }
        userRepo.updateUser(user);
    }


}
