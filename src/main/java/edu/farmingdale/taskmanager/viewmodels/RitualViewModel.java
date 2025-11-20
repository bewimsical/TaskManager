package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseRitualRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class RitualViewModel {

    private final FirebaseRitualRepository ritualRepo = new FirebaseRitualRepository();
    private final StringProperty date = new SimpleStringProperty();
    private final ObservableList<Ritual> morningRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> middayRituals = FXCollections.observableArrayList();
    private final ObservableList<Ritual> eveningRituals = FXCollections.observableArrayList();

    private final Map<String, List<Ritual>> rituals;
    private final User user;
    public RitualViewModel() {
        Session session = Session.getInstance();
        user = session.getUser();
        rituals = session.getRituals();
        morningRituals.addAll(rituals.get("Morning"));
        middayRituals.addAll(rituals.get("Midday"));
        eveningRituals.addAll(rituals.get("Evening"));

        date.set(formatDate(LocalDate.now()));
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
        choreCard.redraw();

    }

    public Map<String, List<Ritual>> getRituals() {
        return rituals;
    }
}
