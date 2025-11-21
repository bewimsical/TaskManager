package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseRitualRepository;
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

    private final FirebaseRitualRepository ritualRepo = new FirebaseRitualRepository();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty streak = new SimpleStringProperty();
    private final StringProperty xpBonus = new SimpleStringProperty();
    private final DoubleProperty xpPercent = new SimpleDoubleProperty();
    private final ObservableList<Ritual> morningRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> middayRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> eveningRituals = FXCollections.observableArrayList();

    private final Map<String, ObjectProperty<Image>> dayImages = new HashMap<>();
    private final Image doneImage  = new Image(TaskManagerApplication.class.getResource("images/ritualgem.png").toExternalForm());
    private final Image emptyImage = new Image(TaskManagerApplication.class.getResource("images/retualgem-empty2.png").toExternalForm());

    private final Map<String, List<Ritual>> rituals;
    private final Map<String, Boolean> weekStreak;
    private final User user;
    public RitualViewModel() {
        Session session = Session.getInstance();
        user = session.getUser();
        rituals = session.getRituals();
        weekStreak = user.getWeekStreak();

        for (int i = 1; i <= 7; i++) {
            ObjectProperty<Image> property = new SimpleObjectProperty<>();
            dayImages.put(String.valueOf(i), property);
        }


        date.set(formatDate(LocalDate.now()));
    }

    public ObjectProperty<Image> imageProperty(String day) {
        return dayImages.get(day);
    }

    public void updateDay(String day) {
        boolean value = weekStreak.getOrDefault(day, false);
        dayImages.get(day).set(value ? doneImage : emptyImage);
    }

    public void setUpViews(){
        morningRituals.addAll(rituals.get("Morning"));
        middayRituals.addAll(rituals.get("Midday"));
        eveningRituals.addAll(rituals.get("Evening"));

        for (int i = 1; i <= 7; i++) {
            updateDay(String.valueOf(i));
        }
    }

    public StringProperty dateProperty(){
        return date;
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

    public void createRitual(Chore chore, String time){
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
            case "Morning" -> morningRituals.add(ritual);
            case "Midday" -> middayRituals.add((ritual));
            case "Evening" -> eveningRituals.add((ritual));
        }
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

    public void completeChore(ChoreCard choreCard){
        Ritual ritual = (Ritual) choreCard.getData();
        ritual.setCompleted(true);
        //move xp bar

        //check all chores
        boolean dayComplete = rituals.values().stream()
                        .flatMap(List::stream)
                        .allMatch(Ritual::isCompleted);
        if (dayComplete){
            //update database
            String day = String.valueOf(LocalDate.now().getDayOfWeek().getValue());
            weekStreak.put(day, true);
            updateDay(day);
        }

        choreCard.redraw();

    }

    public Map<String, List<Ritual>> getRituals() {
        return rituals;
    }

    public Map<String, Boolean> getWeekStreak() {
        return weekStreak;
    }
}
