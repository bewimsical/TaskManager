package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseRitualRepository;
import edu.farmingdale.taskmanager.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.UUID;

public class RitualViewModel {

    private final FirebaseRitualRepository ritualRepo = new FirebaseRitualRepository();
    private final StringProperty date = new SimpleStringProperty();

    private final User user;
    public RitualViewModel() {
        Session session = Session.getInstance();
        user = session.getUser();
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
                .timeOfDay(Ritual.TimeOfDay.valueOf(time))
                .xp(100)
                .build();
        ritualRepo.setRitual(ritual, user);
    }
}
